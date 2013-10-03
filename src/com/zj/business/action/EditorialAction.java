package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Editorial;
import com.zj.business.service.IEditorialService;
import com.zj.business.vo.EditorialVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.DateUtil;
import com.zj.common.utils.FileUtil;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("editorialAction")
@Scope("prototype")
public class EditorialAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 918753298317434213L;

	private static final Logger log = Logger.getLogger(EditorialAction.class);
	private Editorial editorial;
	private Brand brand;
	@Resource
	private IEditorialService editorialService;
	private String errorMsg;
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	private String query;
	private String qtype;
	
	private File[] imageFiles;
	private String[] imageFilesContentType;
	private String[] imageFilesFileName;
	
	public String save(){
		try{
			String attachmentDirPath = "upload/editorial/"+DateUtil.date2Str(new Date(),"yyyyMMdd")+"_"+System.currentTimeMillis();
			String absoluteUrl = getBasePath()+attachmentDirPath;
			File dir = new File(absoluteUrl);
			if(!dir.exists()){
				dir.mkdirs();
			}
			editorial.setImgs(attachmentDirPath);
			editorial.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			editorial.setCreateTime(new Date());
			editorialService.save(editorial, brand);
			
			if(imageFiles != null){
				for(int i=0;i<imageFiles.length;i++){
					String attachFileName = (i+1)+getExtention(imageFilesFileName[i]);
					String filePath = absoluteUrl+"/"+attachFileName;
					File destFile = new File(filePath);
					copyByChannel(imageFiles[i], destFile);
				}
			}
			getValueStack().set("msg","Add Editorial ["+editorial.getEditorialEname()+"] successfully!");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("Add editorial error!",se);
			getValueStack().set("msg", "Add Editorial ["+editorial.getEditorialEname()+"] info failure!" );
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.info("upload attachments error", ue);
			getValueStack().set("msg", "Add Editorial ["+editorial.getEditorialEname()+"] success, but upload attachments occured error!" );
			return "save_failure";
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "Add Editorial ["+editorial.getEditorialEname()+"] failed , because session timeout, please relogin!" );
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Editorial> pageinfo = editorialService.loadEditorialsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			editorialService.bulkDelete(Editorial.class, keys);
			String msg = "Delete[" + keys.length + "] Editorial Images Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete  Editorial Images Failure !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
			log.debug("bulk delete editorial failed !",e);
		}
	}

	public void doSearch(){
		try {
			PageInfo<Editorial> pageinfo = editorialService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String modifyForward(){
		try {
			Editorial dbEditorial = editorialService.get(Editorial.class, id);
			getValueStack().setValue("editorial", dbEditorial);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			log.debug("cannot find this editorial in db",e);
			getValueStack().setValue("msg", "there occurred some error! please attempt again!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		String attachmentDirPath = editorial.getImgs();
		try{
			editorial.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			editorial.setModifiedTime(new Date());
			editorialService.update(editorial,brand);
			if(imageFiles != null){
				String absoluteUrl = getBasePath()+attachmentDirPath;
				
				if(FileUtil.isDirectory(absoluteUrl)){
					File directory = new File(absoluteUrl);
					preDeleteDirectory(directory);
					//add new files
					for(int i=0;i<imageFiles.length;i++){
						String imageFileName = (i+1)+getExtention(imageFilesFileName[i]);
						String fileUrl = absoluteUrl+"/"+imageFileName;
						File destFile = new File(fileUrl);
						FileUtil.copyFile(imageFiles[i], destFile);
					}
				}
			}
			getValueStack().set("msg", "update editorial ["+editorial.getEditorialEname()+"] successfully");
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("update editorial error!", se);
			getValueStack().set("msg", "Update editorial ["+editorial.getEditorialEname()+"] info failed!" );
		}catch(UploadFileException ue){
			getValueStack().set("msg", "Update editorial ["+editorial.getEditorialEname()+"] info success, but upload attachments occured error!" );
			ue.printStackTrace();
			log.debug("upload attachments error!",ue);
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "Update editorial ["+editorial.getEditorialEname()+"] failed, seesion timeout , please relogin!");
		}
		return "modify";
	}
	
	//below methods for frontend
	public String showByBrand(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try {
			List<Editorial> editorials = editorialService.getEditorialByBrand(brand.getBrandid());
			List<EditorialVO> vos = new ArrayList<EditorialVO>();
			String basePath = getBasePath();
			for(Editorial editorial:editorials){
				EditorialVO vo = new EditorialVO(editorial,basePath);
				vo.process(language);
				vos.add(vo);
			}
			getValueStack().set("editoriallist",vos);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "load_editorial_success";
	}
		
	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public File[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(File[] imageFiles) {
		this.imageFiles = imageFiles;
	}

	public String[] getImageFilesContentType() {
		return imageFilesContentType;
	}

	public void setImageFilesContentType(String[] imageFilesContentType) {
		this.imageFilesContentType = imageFilesContentType;
	}

	public String[] getImageFilesFileName() {
		return imageFilesFileName;
	}

	public void setImageFilesFileName(String[] imageFilesFileName) {
		this.imageFilesFileName = imageFilesFileName;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
