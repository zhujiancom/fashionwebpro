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
import com.zj.business.po.Lookbook;
import com.zj.business.service.ILookbookService;
import com.zj.business.vo.LookbookVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.DateUtil;
import com.zj.common.utils.FileUtil;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("lookbookAction")
@Scope("prototype")
public class LookbookAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8914523696124281679L;
	private static final Logger log = Logger.getLogger(LookbookAction.class);
	private Lookbook lookbook;
	private Brand brand;
	@Resource
	private ILookbookService lookbookService;
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
			String attachmentDirPath = "upload/lookbook/"+DateUtil.date2Str(new Date(),"yyyyMMdd")+"_"+System.currentTimeMillis();
			String absoluteUrl = getBasePath()+attachmentDirPath;
			File dir = new File(absoluteUrl);
			if(!dir.exists()){
				dir.mkdirs();
			}
			lookbook.setImgs(attachmentDirPath);
			lookbook.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			lookbook.setCreateTime(new Date());
			lookbookService.save(lookbook,brand);
			if(imageFiles != null){
				for(int i=0;i<imageFiles.length;i++){
					String attachFileName = (i+1)+getExtention(imageFilesFileName[i]);
					String filePath = absoluteUrl+"/"+attachFileName;
					File destFile = new File(filePath);
					copyByChannel(imageFiles[i], destFile);
				}
			}
			getValueStack().set("msg","Add Lookbook ["+lookbook.getLookbookEname()+"] Successfully!");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("Add lookbook error!",se);
			getValueStack().set("msg", "Add Lookbook ["+lookbook.getLookbookEname()+"] failure!" );
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.info("upload attachments error", ue);
			getValueStack().set("msg", "Add Lookbook ["+lookbook.getLookbookEname()+"] success,but upload attachments occured error!" );
			return "save_failure";
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "Add Lookbook ["+lookbook.getLookbookEname()+"] failed, because session time out, please relogin!" );
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Lookbook> pageinfo = lookbookService.loadLookbooksForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			lookbookService.bulkDelete(Lookbook.class, keys);
			String msg = "Delete[" + keys.length + "] Lookbook Images Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Lookbook Images Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug(e);
			sendJSONdata(json);
		}
	}

	public void doSearch(){
		try {
			PageInfo<Lookbook> pageinfo = lookbookService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String modifyForward(){
		try {
			Lookbook dbLookbook = lookbookService.get(Lookbook.class, id);
			getValueStack().setValue("lookbook", dbLookbook);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("cannot find this lookbook in db",e);
			getValueStack().setValue("msg", "there occurred some error! please attempt again!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		String attachmentDirPath = lookbook.getImgs();
		try{
			lookbook.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			lookbook.setModifiedTime(new Date());
			lookbookService.update(lookbook, brand);
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
			getValueStack().set("msg", "update lookbook ["+lookbook.getLookbookEname()+"] successfully");
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("update lookbook error!", se);
			getValueStack().set("msg", "update lookbook ["+lookbook.getLookbookEname()+"] failure!" );
		}catch(UploadFileException ue){
			log.debug("upload attachments error!",ue);
			getValueStack().set("msg", "update lookbook ["+lookbook.getLookbookEname()+"] success,but upload attactments occured error!" );
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "update lookbook ["+lookbook.getLookbookEname()+"] failure,because of session time out , please relogin!");
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
			List<Lookbook> lookbooks = lookbookService.getLookbookByBrand(brand.getBrandid());
			List<LookbookVO> vos = new ArrayList<LookbookVO>();
			String basePath = getBasePath();
			for(Lookbook lookbook:lookbooks){
				LookbookVO vo = new LookbookVO(lookbook,basePath);
				vo.process(language);
				vos.add(vo);
			}
			getValueStack().set("lookbooklist",vos);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "load_lookbook_success";
	}
	
	public Lookbook getLookbook() {
		return lookbook;
	}

	public void setLookbook(Lookbook lookbook) {
		this.lookbook = lookbook;
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
