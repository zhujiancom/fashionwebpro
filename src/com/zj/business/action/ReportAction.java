package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID; 

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.po.Designer;
import com.zj.business.po.Report;
import com.zj.business.service.IReportService;
import com.zj.business.vo.ReportVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
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
	@Value("#{envConfig['upload.report.dir']}")
	private String fileUploadPath;
	
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	private String query;
	private String qtype;
	private File imageFile;
	private String imageFileContentType;
	private String imageFileFileName;
	
	public String save(){
		String imgUrl = "";
		String thumbnailUrl = "";
		boolean isAddImg = false;
		try{
			if(imageFileFileName != null && !"".equals(imageFileFileName)){
				isAddImg = true;
				String fileType = getExtention(imageFileFileName);
				String imageName = UUID.randomUUID().toString();
				imgUrl = fileUploadPath+imageName+fileType;
				thumbnailUrl = fileUploadPath+imageName+CommonConstant.ThumbnailSuffix+ getExtention(imageFileFileName);
				report.setReportimg(imgUrl);
			}
			report.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			report.setCreateTime(new Date());
			reportService.save(report,designer);
			
			if(isAddImg){
				String originalUrl = getBasePath() + imgUrl;
				String thumbnail = getBasePath() +thumbnailUrl;
				uploadSingleImage(imageFile,originalUrl,thumbnail);
			}
			getValueStack().set("msg","Add report ["+report.getReportEname()+"] Successfully!");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("add report error!",se);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] failure! root cause is "+se.getMessage() );
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.info("upload attachments error!", ue);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] success, but upload attachments occured error!" );
			return "save_failure";
		}catch(Exception e){
			log.debug(e);
			getValueStack().set("msg", "Add report ["+report.getReportEname()+"] failure! root cause is "+e.getMessage() );
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
			Designer d = dbReport.getDesigner();
			ReportVO rvo = VOFactory.getObserverVO(ReportVO.class, dbReport);
			getValueStack().set("reportvo", rvo);
			getValueStack().set("designer", d);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("cannot find this report in db",e);
			getValueStack().set("msg", "there occurred one error ["+e.getMessage()+"]! please attempt again!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		String imgurl = "";
		String thumbnailUrl="";
		String oldImagePath = getBasePath() + report.getReportimg();
		boolean isUpdateImg = false;
		try{
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				String fileType = getExtention(imageFileFileName);
				isUpdateImg = true;
				String imageName =UUID.randomUUID().toString();
				imgurl = fileUploadPath + imageName+fileType;
				thumbnailUrl=fileUploadPath+imageName+CommonConstant.ThumbnailSuffix+fileType;
				report.setReportimg(imgurl);
			}
			report.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			report.setModifiedTime(new Date());
			reportService.update(report,designer);
			if(isUpdateImg){
				preDeleteFile(oldImagePath);
				String originalPath = getBasePath() + imgurl;
				String thumbnail = getBasePath()+thumbnailUrl;
				uploadSingleImage(imageFile, originalPath, thumbnail);
			}
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] successfully");
		}catch(ServiceException se){
			se.printStackTrace();
			log.info("update report error!",se);
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] failure! root cause is "+se.getMessage() );
		}catch(UploadFileException ue){
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] info success, but upload attachments occured error!" );
			log.debug("upload attachment error!",ue);
		}catch(Exception e){
			log.debug(e);
			getValueStack().set("msg", "update report ["+report.getReportEname()+"] failure, root cause is "+e.getMessage());
		}
		return "modify";
	}
	// below methods are using in frontend
	public String showReports(){
		Language language = Language.getInstance();
		try {
			List<Report> reports = reportService.getReportsByDesinger(designer.getDesignerId());
			List<ReportVO> reportlist = new ArrayList<ReportVO>();
			for(Report report:reports){
				ReportVO vo = VOFactory.getObserverVO(ReportVO.class, report);
				reportlist.add(vo);
				language.addObserver(vo);
			}
			language.setLanguage(getLanguageType());
			getValueStack().set("reportvolist", reportlist);
			return "load_reports_success";
		} catch (ServiceException e) {
			getValueStack().set("msg", e.getMessage());
			return "load_reports_failure";
		}
	}
	
//	public String showDetails(){
//		Language language = Language.getInstance();
//		try {
//			Report dbreport = reportService.get(Report.class, report.getReportid());
//			ReportVO vo = VOFactory.getObserverVO(ReportVO.class, dbreport);
//			language.addObserver(vo);
//			language.setLanguage(getLanguageType());
//			getValueStack().set("specreport", vo);
//			return "open_report_success";
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			getValueStack().set("msg",e.getMessage());
//			return "open_report_failure";
//		}
//	}
//	
//	public String showPreItem1(){
//		Language language = Language.getInstance();
//		try {
//			Report preReport = reportService.getPreReport(report.getReportid());
//			ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, preReport);
//			language.addObserver(vo);
//			language.setLanguage(getLanguageType());
//			getValueStack().set("specreport", vo);
//			return "open_report_success";
//		} catch (ServiceException e) {
//			log.debug(e);
//			try {
//				report = reportService.get(Report.class, report.getReportid());
//				ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, report);
//				language.addObserver(vo);
//				language.setLanguage(getLanguageType());
//				getValueStack().set("specreport",vo);
//			} catch (ServiceException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			return "open_report_failure";
//		}
//	}
	
	/////////////////////////////////////////////// new function ////////////////////////////////////////
	public String showDetail(){
		Language language = Language.getInstance();
		try {
			Report dbreport = reportService.get(Report.class, report.getReportid());
			ReportVO vo = VOFactory.getObserverVO(ReportVO.class, dbreport);
			language.addObserver(vo);
			language.setLanguage(getLanguageType());
			getValueStack().set("reportvo", vo);
			return "show_report_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			getValueStack().set("msg",e.getMessage());
			return "serviceException";
		}
	}
	public String showPreItem(){
		Language language = Language.getInstance();
		try {
			Report preReport = reportService.getPreReport(report.getReportid());
			ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, preReport);
			language.addObserver(vo);
			language.setLanguage(getLanguageType());
			getValueStack().set("reportvo", vo);
			return "show_report_success";
		} catch (ServiceException e) {
			log.debug(e);
			try {
				report = reportService.get(Report.class, report.getReportid());
				ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, report);
				language.addObserver(vo);
				language.setLanguage(getLanguageType());
				getValueStack().set("reportvo",vo);
				return "show_report_success";
			} catch (ServiceException e1) {
				e1.printStackTrace();
				return "serviceException";
			}
		}
	}
	
	public String showNextItem(){
		Language language = Language.getInstance();
		try {
			Report preReport = reportService.getNextReport(report.getReportid());
			ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, preReport);
			language.addObserver(vo);
			language.setLanguage(getLanguageType());
			getValueStack().set("reportvo", vo);
			return "show_report_success";
		} catch (ServiceException e) {
			log.debug(e);
			try {
				report = reportService.get(Report.class, report.getReportid());
				ReportVO vo =  VOFactory.getObserverVO(ReportVO.class, report);
				language.addObserver(vo);
				language.setLanguage(getLanguageType());
				getValueStack().set("reportvo",vo);
				return "show_report_success";
			} catch (ServiceException e1) {
				e1.printStackTrace();
				return "serviceException";
			}
		}
	}
/////////////////////////////////////////////// new function ////////////////////////////////////////
	
	public String loadReportsByOrder(){
		Language language = Language.getInstance();
		try {
			List<Report> repotrs = reportService.loadAllReportsByDESC();
			List<ReportVO> vos = new LinkedList<ReportVO>();
			for(Report report:repotrs){
				ReportVO vo = VOFactory.getObserverVO(ReportVO.class, report);
				language.addObserver(vo);
				vos.add(vo);
			}
			language.setLanguage(getLanguageType());
			getValueStack().set("reportvolist", vos);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "load_success";
	}
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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
}
