package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Style;
import com.zj.business.service.IStyleService;
import com.zj.business.vo.StyleVO;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.log.Log;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("styleAction")
@Scope("prototype")
public class StyleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -972128944133920008L;

	private Style style;
	@Resource
	private IStyleService styleService;
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	private String query;
	private String qtype;
	
	private File imageFile; // 文件名
	private String imageFileContentType;  // 文件名 + ContentType (固定写法)
	private String imageFileFileName;  //文件名 + FileName (固定写法)
	
	public String save(){
		String imgUrl = "";
		boolean isAddImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isAddImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgUrl = "upload/headImg/style/"+imageFileName;
				style.setStyleimg(imgUrl);
			}
			style.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			style.setCreateTime(new Date());
			styleService.insert(style);
			if(isAddImg){
				String absoluteUrl = getBasePath()+imgUrl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile,destFile);
			}
			getValueStack().set("msg", "create Style "+style.getStyleEname()+" Successfully! ");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			Log.debug(StyleAction.class, se.getMessage());
			getValueStack().set("msg", "create Style "+style.getStyleEname()+" Failure! ");
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			Log.debug(StyleAction.class, ue.getMessage());
			getValueStack().set("msg", "create Style "+style.getStyleEname()+" Failure! ");
			return "save_failure";
		}
		catch(Exception e){
			getValueStack().set("msg", "create Style "+style.getStyleEname()+" Failure! ");
			Log.debug(StyleAction.class, e.getMessage());
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
			PageInfo<Style> pageinfo = styleService.loadStylesForPage(rp, page);
//			Map<String,String> extraAttr = new HashMap<String,String>();
//			extraAttr.put("checked", "checked");
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			styleService.bulkDelete(Style.class, keys);
			String msg = "Delete[" + keys.length + "] Styles Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Styles Failure !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
			Log.debug(StyleAction.class, e.getMessage());
		}
	}
	
	public String modifyForward(){
		try {
			Style dbstyle = styleService.get(Style.class, id);
			getValueStack().setValue("style", dbstyle);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			Log.debug(StyleAction.class, "The style is not exist!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		UpdateMode mode = UpdateMode.MINI;
		String imgurl = "";
		String oldImgurl = getBasePath()+style.getStyleimg();
		boolean isUpdateImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				mode = UpdateMode.NORMAL;
				isUpdateImg = true;
				String imageFileName = new Date().getTime()+getExtention(imageFileFileName);
				imgurl = "upload/headImg/style/"+imageFileName;
				style.setStyleimg(imgurl);
			}
			style.setModifiedTime(new Date());
			style.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
//			styleService.merge(style, style.getStyleid(), mode);
			if(isUpdateImg){
				if(oldImgurl != null){
					File oldFile = new File(oldImgurl);
					if(oldFile.exists()){
						oldFile.delete();
					}
				}
				String absolutePath = getBasePath()+imgurl;
				File destFile = new File(absolutePath);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile, destFile);
			}
			getValueStack().set("msg", "update Style ["+style.getStyleEname()+"] successfully!");
			return "modify";
		}catch(UploadFileException ue){
			getValueStack().set("msg", "update  Style ["+style.getStyleEname()+"] failure!");
			ue.printStackTrace();
			Log.debug(StyleAction.class, ue.getMessage());
			return "modify";
		}
		catch(Exception e){
			e.printStackTrace();
			Log.debug(StyleAction.class, e.getMessage());
			getValueStack().set("msg", "update Style ["+style.getStyleEname()+"] failure!");
			return "modify";
		}
	}
	
	public void doSearch(){
		try {
			PageInfo<Style> pageinfo = styleService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	//below methods all for frontend
	public String loadAll(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try {
			List<Style> styles = styleService.getAll(Style.class);
			List<StyleVO> stylevos = new ArrayList<StyleVO>();
			for(Style s: styles){
				StyleVO svo = new StyleVO(s);
//				svo.process(language);
				stylevos.add(svo);
			}
			getValueStack().set("stylelist", stylevos);
			return "load_styles_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "load_styles_failure";
		}
	}
	
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
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
