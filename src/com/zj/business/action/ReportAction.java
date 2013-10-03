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
import com.zj.business.po.Designer;
import com.zj.business.po.Report;
import com.zj.business.service.IReportService;
import com.zj.business.vo.ReportVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.DateUtil;
import com.zj.common.utils.FileUtil;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("reportAction")
@Scope("prototype")
public class ReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906604293208315942L;
	private static final Logger log = Logger.getLogger(ReportAction.class);
	private Report report;
	private Designer designer;
	@Resource
	private IReportService reportService;
	
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
			String attachmentDirPath = "upload/presreport/"+DateUtil.date2Str(new Date(),"yyyyMMdd")+"_"+System.currentTimeMillis();
			String absoluteUrl = getBasePath()+attachmentDirPath;
			File dir = new File(absoluteUrl);
			if(!dir.exists()){
				dir.mkdirs();
			}
			report.setReportimg(attachmentDirPath);
			report.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			report.setCreateTime(new Date());
			reportService.update(report,designer);
			
			if(imageFiles != null){
				for(int i=0;i<imageFiles.length;i++){
					String attachFileName = (i+1)+getExtention(imageFilesFileName[i]);
					String filePath = absoluteUrl+"/"+attachFileName;
					File destFile = new File(filePath);
					copyByChannel(imageFiles[i], destFile);
				}
			}
			getValueStack().set("msg","Add report ["+report.getReportEname()+"] Successfully!");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("add report error!",se);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] failure!" );
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.info("upload attachments error!", ue);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] success, but upload attachments occured error!" );
			return "save_failure";
		}catch(Exception e){
			log.debug(e);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] failure! please login again!" );
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Report> pageinfo = reportService.loadreportsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			reportService.bulkDelete(Report.class, keys);
			String msg = "Delete[" + keys.length + "] Reports Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Reports Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug("delete reports failed!",e);
			sendJSONdata(json);
		}
	}

	public void doSearch(){
		try {
			PageInfo<Report> pageinfo = reportService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String modifyForward(){
		try {
			Report dbReport = reportService.get(Report.class, id);
			getValueStack().setValue("report", dbReport);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("cannot find this report in db",e);
			getValueStack().setValue("msg", "there occurred some error! please attempt again!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		String attachmentDirPath = report.getReportimg();
		try{
			report.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			report.setModifiedTime(new Date());
			reportService.update(report,designer);
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
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] successfully");
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("update report error!",se);
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] failure!" );
		}catch(UploadFileException ue){
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] failure!" );
			log.debug("upload attachment error!",ue);
		}catch(Exception e){
			log.debug(e);
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] failure!");
		}
		return "modify";
	}
	// below methods are using in frontend
	public String showReports(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 

		try {
			List<Report> reports = reportService.getReportsByDesinger(designer.getDesignerId());
			List<ReportVO> reportlist = new ArrayList<ReportVO>();
			String basePath = getBasePath();
			for(Report report:reports){
				ReportVO vo = new ReportVO(report,basePath);
				vo.process(language);
				reportlist.add(vo);
			}
			getValueStack().set("reportlist", reportlist);
			return "load_reports_success";
		} catch (ServiceException e) {
			getValueStack().set("msg", e.getMessage());
			return "load_reports_failure";
		}
	}
	
	public String showDetails(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try {
			Report dbreport = reportService.get(Report.class, report.getReportid());
			ReportVO vo = new ReportVO(dbreport, getBasePath());
			vo.process(language);
			getValueStack().set("specreport", vo);
			return "open_report_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			getValueStack().set("msg",e.getMessage());
			return "open_report_failure";
		}
	}
	
	public String showPreItem(){
		try {
			Report preReport = reportService.getPreReport(report.getReportid());
			ReportVO vo = new ReportVO(preReport, getBasePath());
			getValueStack().set("specreport", vo);
			return "open_report_success";
		} catch (ServiceException e) {
			log.debug(e);
			try {
				report = reportService.get(Report.class, report.getReportid());
				ReportVO vo = new ReportVO(report, getBasePath());
				getValueStack().set("specreport",vo);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "open_report_failure";
		}
	}
	
	public String showNextItem(){
		return null;
	}
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
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
}
