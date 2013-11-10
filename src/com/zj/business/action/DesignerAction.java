package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ckfinder.connector.utils.ImageUtils;
import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.service.IDesignerService;
import com.zj.business.treenode.DesignerMenuBuilder;
import com.zj.business.treenode.IMenuBuilder;
import com.zj.business.treenode.Menu;
import com.zj.business.vo.BrandVO;
import com.zj.business.vo.DesignerVO;
import com.zj.business.vo.MenuVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("designerAction")
@Scope("prototype")
public class DesignerAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214609837621906826L;
	private static final Logger log = Logger.getLogger(DesignerAction.class);
	
	private Designer designer;
	@Resource
	private IDesignerService designerService;
	@Value("#{envConfig['upload.designer.dir']}")
	@Resource
	private Language language;
	private String fileUploadPath;
	private String errorMsg;
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private long id; // user id which need to be modify
	private String treeid;
	private File imageFile;  // 文件名
	private String imageFileContentType;  // 文件名 + ContentType (固定写法)
	private String imageFileFileName;  //文件名 + FileName (固定写法)
	private String query;
	private String qtype;
	
	//ztree param
	private String nodetype;
	private JSONArray jsonArray;
	
	private String term;  //fuzzy search designer's full name
	public String save(){
		String imgUrl = "";
		String thumbnailUrl = "";
		boolean isAddImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isAddImg = true;
				String imageName = UUID.randomUUID().toString();
				imgUrl = fileUploadPath+imageName+getExtention(imageFileFileName);
				thumbnailUrl = fileUploadPath+imageName+ CommonConstant.ThumbnailSuffix +getExtention(imageFileFileName);
				designer.setImgURL(imgUrl);
			}
			designer.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			designer.setCreateTime(new Date());
			designerService.insert(designer);
			if(isAddImg){
				String absoluteUrl = getBasePath()+imgUrl;
				String absoluteThumbnailUrl = getBasePath() +thumbnailUrl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(imageFile,destFile);
				ImageUtils.createResizedImage(imageFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			}
			getValueStack().set("msg", "create designer ["+designer.getEname()+"] Successfully! ");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("save designer "+designer.getEname()+" occured error!",se);
			getValueStack().set("msg", "create designer ["+designer.getEname()+"] Failure! ");
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("upload attachments occured error!",ue);
			getValueStack().set("msg", "create designer ["+designer.getEname()+"] success, but upload headImg occured error! ");
			return "save_failure";
		}
		catch(Exception e){
			getValueStack().set("msg", "create designer ["+designer.getEname()+"] Failure! root cause is "+e.getMessage());
			log.debug(e);
			if(!"".equals(imgUrl)){
				File destFile = new File(imgUrl);
				if(destFile.exists()){
					destFile.delete();
				}
			}
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Designer> pageinfo = designerService.loadDesignersForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	
	public void showAllExcludeFeaturedDesigner(){
		try{
			PageInfo<Designer> pageinfo = designerService.loadDesignersExcludeFaturedForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		}catch(ServiceException e){
			
		}
	}
	
	public void fuzzySearch(){
		try {
			List<Designer> designers = designerService.fuzzySearchByName(term);
			List<String> names = new ArrayList<String>();
			for(Designer d:designers){
				names.add(d.getEname());
			}
			String json = JSONUtil.listToJson(names);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			designerService.bulkDelete(Designer.class, keys);
			String msg = "Delete[" + keys.length + "] Designers Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Designers Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug("delete designers failed!",e);
			sendJSONdata(json);
		}
	}
	
	public String modifyForward(){
		try {
			Designer dbdesigner = designerService.get(Designer.class, id);
			DesignerVO vo = new DesignerVO(dbdesigner);
			getValueStack().set("designervo", vo);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The Designer is not exist!",e);
			return "modify_forward_failure";
		}catch(Exception e){
			log.debug(e);
			e.printStackTrace();
			return "modify_forward_failure";
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：update designer info
	 *
	 * 方法名称：update
	 *
	 * 所在类名：DesignerAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-9-29 下午10:04:22
	 *
	 *
	 * @return
	 */
	public String update(){
		String imgurl = "";
		String thumbnailUrl="";
		String oldImgurl = getBasePath()+designer.getImgURL();
		boolean isUpdateImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				String fileType = getExtention(imageFileFileName);
				isUpdateImg = true;
				String imageName =UUID.randomUUID().toString();
				imgurl = fileUploadPath+imageName+fileType;
				thumbnailUrl=fileUploadPath+imageName+CommonConstant.ThumbnailSuffix+fileType;
				designer.setImgURL(imgurl);
			}
			designer.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			designer.setModifiedTime(new Date(System.currentTimeMillis()));
			designerService.update(designer);
			if(isUpdateImg){
				preDeleteFile(oldImgurl);
				String absolutePath = getBasePath()+imgurl;
				String absoluteThumbnailUrl = getBasePath() +thumbnailUrl;
				File destFile = new File(absolutePath);
				if(!destFile.getParentFile().exists()){
					if(!destFile.getParentFile().mkdirs()){
						throw new UploadFileException("create parent floder error!");
					}
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(imageFile, destFile);
				ImageUtils.createResizedImage(imageFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			}
			getValueStack().set("msg", "update Designer ["+designer.getEname()+"] successfully!");
		}catch(ServiceException se){
			getValueStack().set("msg", "update Designer ["+designer.getEname()+"]failure!");
			se.printStackTrace();
			log.debug("update designer occured error!",se);
		}
		catch(UploadFileException ue){
			getValueStack().set("msg", "update Designer ["+designer.getEname()+"] success, but upload head image occured error!");
			ue.printStackTrace();
			log.debug("upload attachment occured error!",ue);
		}
		catch(Exception e){
			e.printStackTrace();
			getValueStack().set("msg", "update Designer ["+designer.getEname()+"] failed, root cause is "+e.getMessage());
		}
		return "modify";
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：search specified record in flexgrid
	 *
	 * 方法名称：doSearch
	 *
	 * 所在类名：DesignerAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-9-29 下午09:52:20
	 *
	 *
	 */
	public void doSearch(){
		try {
			PageInfo<Designer> pageinfo = designerService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
			String json = e.getMessage();
			sendJSONdata(json);
		}
	}
	
	// below methods all requests from frontend
	public String loadAll(){
		try {
			List<Designer> designers = designerService.getAll(Designer.class);
			List<DesignerVO> designervos = new ArrayList<DesignerVO>();
			for(Designer d:designers){
				DesignerVO dvo = VOFactory.getObserverVO(DesignerVO.class, d);
				designervos.add(dvo);
				language.addObserver(dvo);
			}
			language.setLanguage(getLanguageType());
			getValueStack().set("designers",designervos);
			return "load_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "serviceException";
		} catch (Exception e){
			log.error("Server Error",e);
			return "exception";
		}
	}
	
	public String showProfile(){
		try {
			Long id = designer.getDesignerId();
			Designer d = designerService.get(Designer.class,id);
			DesignerVO dvo = VOFactory.getObserverVO(DesignerVO.class, d);
			language.addObserver(dvo);
			language.setLanguage(getLanguageType());
			getValueStack().set("designervo",dvo);
			return "load_profile_success";
		} catch (ServiceException e) {
			log.error("Service Error",e);
			getValueStack().set("msg","forward profile page error,because "+e.getMessage());
			return "load_profile_failure";
		} catch(Exception e){
			log.error("Server Error",e);
			return "exception";
		}
	}
	
	public String loadMenu(){
		long designerId = designer.getDesignerId();
		try {
			Designer d = designerService.get(Designer.class,designerId);
			DesignerVO dvo = VOFactory.getObserverVO(DesignerVO.class, d);
			language.addObserver(dvo);
			Set<Brand> bs = d.getBrands();
			List<BrandVO> bvos = new LinkedList<BrandVO>();
			for(Brand b:bs){
				BrandVO bvo = VOFactory.getObserverVO(BrandVO.class, b);
				bvos.add(bvo);
				language.addObserver(bvo);
			}
			boolean isPermission = false;
			if(session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null){
				isPermission = true;
			}
			MenuVO mvo = VOFactory.getObserverVO(MenuVO.class);
			language.addObserver(mvo);
			language.setLanguage(getLanguageType());
			IMenuBuilder builder = new DesignerMenuBuilder(dvo,bvos,isPermission);
			List<Menu> menuTree = builder.createMenuTree();
			getValueStack().set("designervo", dvo);
			getValueStack().set("menutree", menuTree);
		} catch (ServiceException e) {
			log.error("Service Error",e);
			return "serviceException";
		} catch(Exception e){
			log.error("Server Error",e);
			return "exception";
		}
		return "loadMenu_success";
	}
	
	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getNodetype() {
		return nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}


}
