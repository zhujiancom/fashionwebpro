package com.zj.business.action;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.service.IDesignerService;
import com.zj.business.vo.DesignerVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;
import com.zj.common.ztreenode.NodeType;
import com.zj.common.ztreenode.TreeNode;
import com.zj.common.ztreenode.TreeNodeImpl;
import com.zj.common.ztreenode.TreeNodeStrategyFactory;
import com.zj.common.ztreenode.ZTreeNode;
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
		boolean isAddImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isAddImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgUrl = "upload/headImg/designer/"+imageFileName;
				designer.setImgURL(imgUrl);
			}
			designer.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			designer.setCreateTime(new Date());
			designerService.insert(designer);
			if(isAddImg){
				String absoluteUrl = getBasePath()+imgUrl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile,destFile);
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
			getValueStack().set("msg", "create designer ["+designer.getEname()+"] Failure! beacuse of session time out! ");
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
			getValueStack().setValue("designer", dbdesigner);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The Designer is not exist!",e);
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
		String oldImgurl = getBasePath()+designer.getImgURL();
		boolean isUpdateImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isUpdateImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgurl = "upload/headImg/designer/"+imageFileName;
				designer.setImgURL(imgurl);
			}
			designer.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			designer.setModifiedTime(new Date(System.currentTimeMillis()));
			designerService.update(designer);
			if(isUpdateImg){
				preDeleteFile(oldImgurl);
				String absolutePath = getBasePath()+imgurl;
				File destFile = new File(absolutePath);
				copyByChannel(imageFile, destFile);
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
			getValueStack().set("msg", "update Designer ["+designer.getEname()+"] failed, because of seesion time out, please relogin!");
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
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try {
			List<Designer> designers = designerService.getAll(Designer.class);
			List<DesignerVO> designervos = new ArrayList<DesignerVO>();
			for(Designer d:designers){
				DesignerVO vo = new DesignerVO(d);
				vo.process(language);
				designervos.add(vo);
			}
			getValueStack().set("designers",designervos);
			return "load_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "load_failure";
		}
	}
	
	public String showProfile(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try {
			Long id = designer.getDesignerId();
			Designer designer = designerService.get(Designer.class,id);
			DesignerVO vo = new DesignerVO(designer);
			vo.process(language);
			getValueStack().set("designer",vo);
			return "load_profile_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			getValueStack().set("msg","forward profile page error,because "+e.getMessage());
			return "load_profile_failure";
		}
	}
	
	public String menuTree(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		long designerId = designer.getDesignerId();
		List<ZTreeNode> menu = new ArrayList<ZTreeNode>();
		AbstractMakeDataStrategy strategy = null;
		if(treeid == null){
			strategy = TreeNodeStrategyFactory.getNodeStrategy(NodeType.NONE,new Object[]{designerId,language});
		}
		if(treeid != null){
			try {
				Designer d = designerService.get(Designer.class,designerId);
				if(nodetype.equals(NodeType.BRAND.toString())){
						Set<Brand> brands = d.getBrands();
						strategy = TreeNodeStrategyFactory.getNodeStrategy(NodeType.BRAND,new Object[]{treeid,brands,language});
				}else if(nodetype.equals(NodeType.PRESS.toString()) && session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null){
					strategy = TreeNodeStrategyFactory.getNodeStrategy(NodeType.PRESS,new Object[]{designerId,treeid,language});
				}else if(nodetype.equals(NodeType.COLLECTIONONE.toString()) && session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null){
					Set<Brand> brands = d.getBrands();
					strategy = TreeNodeStrategyFactory.getNodeStrategy(NodeType.COLLECTIONONE,new Object[]{treeid,brands,language});
				}else if(nodetype.equals(NodeType.COLLECTIONTWO.toString())){
					strategy = TreeNodeStrategyFactory.getNodeStrategy(NodeType.COLLECTIONTWO,new Object[]{treeid,language});
				}else{
					Map<String,String> msg = new HashMap<String,String>();
					msg.put("msg", "please login first!");
					sendJSONdata(msg);
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		TreeNode treenode = new TreeNodeImpl(strategy);
		menu = treenode.generateNode();
		jsonArray = JSONUtil.sendArray(menu, null);
		return "generate_menutree_success";
	}
	
	public void fetchInfo(){
		try {
			Designer fetchFeaturedDesigner = designerService.fetchFeaturedDesigner();
			Blob contentENBlob = fetchFeaturedDesigner.getDetailContentEN();
			String contentEN = StringUtil.getStrFromBlob(contentENBlob);
			Blob contentCHBlob = fetchFeaturedDesigner.getDetailContentCH();
			String contentCH = StringUtil.getStrFromBlob(contentCHBlob);
			List<String> contents = new ArrayList<String>();
			contents.add(contentCH);
			contents.add(contentEN);
			String json = JSONUtil.listToJson(contents);
			sendJSONdata(json);
		} catch (ServiceException e) {
			
		}
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
