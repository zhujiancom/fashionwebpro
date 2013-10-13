package com.zj.business.action;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.po.Brand;
import com.zj.business.po.Editorial;
import com.zj.business.service.IEditorialService;
import com.zj.business.vo.EditorialVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.DateUtil;
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
	@Value("#{envConfig['upload.editorial.dir']}")
	private String fileUploadPath;
	
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
			String attachmentDirPath = fileUploadPath+DateUtil.date2Str(new Date(),"yyyyMMdd")+"_"+System.currentTimeMillis()+"/";
			editorial.setImgs(attachmentDirPath);
			editorial.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			editorial.setCreateTime(new Date());
			editorialService.save(editorial, brand);
			
			if(imageFiles != null){
				String absoluteUrl = getBasePath()+attachmentDirPath;
				preDeleteDirectory(new File(absoluteUrl));
				transferFilesToDirectory(imageFiles,imageFilesFileName,absoluteUrl);
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
			getValueStack().set("msg", "Add Editorial ["+editorial.getEditorialEname()+"] failed , root cause is "+e.getMessage() );
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
			EditorialVO vo = VOFactory.getObserverVO(EditorialVO.class, dbEditorial,getBasePath());
			getValueStack().set("editorialvo", vo);
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
			if(StringUtil.isEmpty(attachmentDirPath) && imageFiles != null && imageFiles.length > 0){
				attachmentDirPath = fileUploadPath+DateUtil.date2Str(new Date(),"yyyyMMdd")+"_"+System.currentTimeMillis()+"/";
				editorial.setImgs(attachmentDirPath);
			}
			editorial.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			editorial.setModifiedTime(new Date());
			editorialService.update(editorial,brand);
			if(imageFiles != null){
				String absoluteUrl = getBasePath()+attachmentDirPath;
				preDeleteDirectory(new File(absoluteUrl));
				//add new files
				transferFilesToDirectory(imageFiles,imageFilesFileName,absoluteUrl);
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
		Language language = Language.getInstance();
		try {
			List<Editorial> editorials = editorialService.getEditorialByBrand(brand.getBrandid());
			List<EditorialVO> vos = new LinkedList<EditorialVO>();
			for(Editorial editorial:editorials){
				EditorialVO vo = VOFactory.getObserverVO(EditorialVO.class, editorial,getBasePath());
				language.addObserver(vo);
				vos.add(vo);
			}
			language.setLanguage(getLanguageType());
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
