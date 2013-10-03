package com.zj.core.control.struts.action;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysModule;
import com.zj.core.po.SysRole;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysModuleService;
import com.zj.core.service.ISysRoleService;
import com.zj.core.service.ISysUserService;

@Component("sysuserAction")
@Scope("prototype")
public class SysUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7661203602453328074L;
	private static final Logger log = Logger.getLogger(SysUserAction.class);
	
	private SysUser user;
	@Resource
	private ISysUserService userService;
	@Resource
	private ISysModuleService moduleService;
	@Resource
	private ISysRoleService roleService;
	private String errorMsg;
	// parameters from flexgird
	private int rp; // page size
	private int page; // page num
	private String query;
	private String qtype;
	// parameters from flexgird
	
	
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	
	// image upload relative params
	
	/*客户端传输的文件信息*/
	private File imageFile;  // 文件名
	private String imageFileContentType;  // 文件名 + ContentType (固定写法)
	private String imageFileFileName;  //文件名 + FileName (固定写法)

	// image upload relative params
	
	/**
	 * user login
	 * @return
	 */
	public String login(){
		SysUser login_user = null;
		try {
			login_user = userService.login(user);
			if (login_user == null) {
				errorMsg = "UserName or Password error !";
				return "login_invalid";
			}
			if (GlobalParam.ENABLE != login_user.getIsEnable()) {
				errorMsg = "The Account has been disabled, please reEnable !";//该账号被禁用,请启用
				return "login_failed";
			}
			Set<SysRole> roles = login_user.getSysRoles();
			List<SysModule> modules = moduleService.loadModulesByRoles(roles);
			session.put(GlobalParam.LOGIN_USER_SESSION, login_user);
			session.put(GlobalParam.LOGIN_USER_MODULE_PRIV, modules);
			return "login_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "login_failed";
		}
	}
	
	/**
	 * show all users
	 */
	public void showAllUsers(){
		try {
			PageInfo<SysUser> pageinfo = userService.loadUserList(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 *
	 * Describle(描述)：search user
	 *
	 * 方法名称：doSearch
	 *
	 * 所在类名：SysUserAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-7 上午10:47:38
	 *
	 *
	 */
	public void doSearch(){
		try {
			PageInfo<SysUser> pageinfo = userService.searchUserList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String json = e.getMessage();
			sendJSONdata(json);
		}
	}
	
	/**
	 *  batch delete user 
	 */
	public void deleteUsers(){
		String json = "";
		try {
			Long[] keys = StringUtil.convertArray(ids);
			userService.bulkDelete(SysUser.class, keys);
			String msg = "Delete[" + keys.length + "] Users Successfully !";
			json = JSONUtil.stringToJson(msg);
		} catch (Exception e) {
			String msg = "Bulk Delete Users Failed !";
			log.debug(e.getMessage());
			json = JSONUtil.stringToJson(msg);
		}
		sendJSONdata(json);
	}

	/**
	 * add a new user
	 */
	public String createUser(){
		String imgUrl = "";
		boolean isAddImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isAddImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgUrl = ServletActionContext.getServletContext().getRealPath("/upload/headImg/backendUser")+"\\"+imageFileName;
				user.setImgUrl(imgUrl);
			}
			Set<SysRole> roles = new HashSet<SysRole>();
			SysRole root = roleService.getRootRole();
			roles.add(root);
			user.setSysRoles(roles);
			user.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			user.setCreateTime(new Date());
			userService.insert(user);
			if(isAddImg){
				File destFile = new File(imgUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile,destFile);
			}
			getValueStack().set("msg", "create user ["+user.getLoginId()+"] Successfully! ");
			return "create_user_successful";
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug(se.getMessage());
			getValueStack().set("msg", "create user ["+user.getLoginId()+"] Failure! ");
			return "create_user_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug(ue.getMessage());
			getValueStack().set("msg", "create user ["+user.getLoginId()+"] Success, but upload file occured error! ");
			return "create_user_failure";
		}
		catch(Exception e){
			getValueStack().set("msg", "create user ["+user.getLoginId()+"] Failed, because of seesion time out, please relogin! ");
			log.debug(e.getMessage());
			return "create_user_failure";
		}
	}
	
	public void disable(){
		String json = "";
		try {
			userService.disable(SysUser.class, id);
			json = JSONUtil
					.stringToJson("The Account [" + user.getLoginId() + "] has been disabled!");
		} catch (ServiceException e) {
			json = JSONUtil.stringToJson(e.getMessage());
			log.debug("disable user error!");
		}
		sendJSONdata(json);
	}
	
	public void enable(){
		String json = "";
		try {
			userService.enable(SysUser.class, id);
			json = JSONUtil
					.stringToJson("The Account[" + user.getLoginId() + "]has been enabled!");
		} catch (ServiceException e) {
			json = JSONUtil.stringToJson(e.getMessage());
			log.debug("enable user error!");
		}
		sendJSONdata(json);
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：preAccquire user data for forwarding modify page
	 *
	 * 方法名称：modifyForward
	 *
	 * 所在类名：SysUserAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-6 下午06:03:58
	 *
	 *
	 * @return
	 */
	public String modifyForward(){
		try {
			SysUser dbUser = userService.get(SysUser.class, id);
			getValueStack().setValue("user", dbUser);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The user is not exist!");
			return "modify_forward_failure";
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：update user info
	 *
	 * 方法名称：updateUser
	 *
	 * 所在类名：SysUserAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-6 下午06:11:55
	 *
	 *
	 * @return
	 */
	public String updateUser1(){
		UpdateMode mode = UpdateMode.MINI;
		String imgurl = "";
		String oldImgurl = user.getImgUrl();
		boolean isUpdateImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				mode = UpdateMode.NORMAL;
				isUpdateImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgurl = ServletActionContext.getServletContext().getRealPath("/upload/headImg/backendUser")+"\\"+imageFileName;
				user.setImgUrl(imgurl);
			}
			user.setModifiedTime(new Date());
			user.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			userService.merge(user, user.getUserId(), mode);
			getValueStack().set("msg", "update User ["+user.getLoginId()+"] successful!");
			if(isUpdateImg){
				if(oldImgurl != null){
					File oldFile = new File(oldImgurl);
					if(oldFile.exists()){
						oldFile.delete();
					}
				}
				File destFile = new File(imgurl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile, destFile);
			}
			return "modify";
		}catch(ServiceException se){
			getValueStack().set("msg", "update User ["+user.getLoginId()+"]failure!");
			se.printStackTrace();
			log.debug(se.getMessage());
			return "modify";
		}
		catch(UploadFileException ue){
			getValueStack().set("msg", "update User ["+user.getLoginId()+"]failure!");
			ue.printStackTrace();
			log.debug(ue.getMessage());
			return "modify";
		}
		catch(Exception e){
			getValueStack().set("msg", "update User ["+user.getLoginId()+"]failure!");
			log.debug(e.getMessage());
			return "modify";
		}
	}
	
	public String updateUser(){
		String imgurl = "";
		String oldImgurl = user.getImgUrl();
		boolean isUpdateImg = false;
		try {
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				isUpdateImg = true;
				String imageFileName = new Date().getTime()
						+ getExtention(imageFileFileName);
				imgurl = ServletActionContext.getServletContext().getRealPath(
						"/upload/headImg/backendUser")
						+ "\\" + imageFileName;
				user.setImgUrl(imgurl);
			}
			user.setModifiedTime(new Date());
			user.setModifier(((SysUser) session
					.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			SysUser target = new SysUser();
			userService.update(target);
			getValueStack().set("msg",
					"update User [" + user.getLoginId() + "] successful!");
			if (isUpdateImg) {
				preDeleteFile(oldImgurl);
				File destFile = new File(imgurl);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile, destFile);
			}
		} catch (ServiceException se) {
			getValueStack().set("msg",
					"update User [" + user.getLoginId() + "]failure!");
			se.printStackTrace();
			log.debug(se.getMessage());
		} catch (UploadFileException ue) {
			getValueStack().set(
					"msg",
					"update User [" + user.getLoginId()
							+ "] info success, but upload head image occured error!");
			ue.printStackTrace();
			log.debug(ue.getMessage());
		} catch (Exception e) {
			getValueStack().set("msg",
					"update User [" + user.getLoginId() + "]failed, because session timeout!");
			log.debug(e.getMessage());
		}
		return "modify";
	}
	
	
	/**
	 * 
	 *
	 * Describle(描述)：loginId uniqueness check
	 *
	 * 方法名称：uniqueLoginIdCheck
	 *
	 * 所在类名：SysUserAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-4 下午09:48:38
	 *
	 *
	 */
	public void uniqueLoginIdCheck(){
		String json = "false";
		try {
			Boolean isUnique = userService.uniquenessCheck(SysUser.class, "loginId", user.getLoginId());
			json = JSONUtil.stringToJson(String.valueOf(isUnique));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("unique check user account failed",e);
			json = "false";
		}
		sendJSONdata(json);
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：user logout
	 *
	 * 方法名称：logout
	 *
	 * 所在类名：SysUserAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-21 下午10:34:14
	 *
	 *
	 * @return
	 */
	public String logout(){
		SysUser u = (SysUser) session.get(GlobalParam.LOGIN_USER_SESSION);
		try {
			userService.logout(u);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		getValueStack().pop();
		session.clear();
		return "logout_success";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	
}
