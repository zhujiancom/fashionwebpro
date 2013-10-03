package com.zj.business.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.playlist.InterviewPlayList;
import com.zj.business.po.Designer;
import com.zj.business.po.Interview;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IInterviewService;
import com.zj.business.vo.InterviewVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.log.Log;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.common.utils.XmlParse;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("interviewAction")
@Scope("prototype")
public class InterviewAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183179471149257186L;

	private Interview interview;
	private Designer designer;
	@Resource
	private IInterviewService interviewService;
	@Resource
	private IDesignerService designerService;
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	private String query;
	private String qtype;
	//video file upload parameters
	private File videoFile;  // 文件名
	private String videoFileContentType;  // 文件名 + ContentType (固定写法)
	private String videoFileFileName;  //文件名 + FileName (固定写法)
	//video file upload parameters
	
	//poster file upload parameters
	private File posterFile;  // 文件名
	private String posterFileContentType;  // 文件名 + ContentType (固定写法)
	private String posterFileFileName;  //文件名 + FileName (固定写法)
	//poster file upload parameters
	
	private String type; // distinguish video or audio
	
	public String save(){
		String posterurl = "";
		String videourl = "";
		boolean isAddVideo = false;
		boolean isAddPoster = false;
		try{
			if(videoFileFileName != null && !"".equals(videoFileFileName)){
				isAddVideo = true;
				String videoFileName = new Date().getTime()+getExtention(videoFileFileName);
				videourl = "upload/interview"+"/"+interview.getInterviewtype()+"/"+videoFileName;
				interview.setInterviewurl(getWebRootPath()+videourl);
			}
			if(posterFileFileName != null && !"".equals(posterFileFileName)){
				isAddPoster = true;
				String posterFileName = new Date().getTime()+getExtention(posterFileFileName);
				posterurl = "upload/interview"+"/"+interview.getInterviewtype()+"/"+posterFileName;
				interview.setPoster(getWebRootPath()+posterurl);
			}
			if(designer != null && !"".equals(designer.getEname())){
				designer = designerService.searchByName(designer.getEname());
				if(designer != null){
					interview.setDesigner(designer);
					designer.getInterviews().add(interview);
				}
			}
			interview.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			interview.setCreateTime(new Date());
			interviewService.insert(interview);
			if(isAddPoster){
				String absoluteUrl = getBasePath()+posterurl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(posterFile,destFile);
			}
			if(isAddVideo){
				String absoluteUrl = getBasePath()+videourl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(videoFile,destFile);
			}
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Successfully! ");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			Log.debug(InterviewAction.class,se.getMessage());
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Failure! ");
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			Log.debug(InterviewAction.class,ue.getMessage());
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Failure! ");
			return "save_failure";
		}
		catch(Exception e){
			e.printStackTrace();
			Log.debug(InterviewAction.class,e.getMessage());
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Failure! ");
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Interview> pageinfo = interviewService.loadInterviewsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			interviewService.bulkDelete(Interview.class, keys);
			String msg = "Delete[" + keys.length + "] Interviews Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Interviews Failure !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
			Log.debug(InterviewAction.class, e.getMessage());
		}
	}

	/**
	 * 
	 *
	 * Describle(描述)：search interview by paramter
	 *
	 * 方法名称：doSearch
	 *
	 * 所在类名：InterviewAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-20 下午05:17:03
	 *
	 *
	 */
	public void doSearch(){
		try {
			PageInfo<Interview> pageinfo = interviewService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
			String json = e.getMessage();
			sendJSONdata(json);
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：forward to modify page after accquirred data
	 *
	 * 方法名称：modifyForward
	 *
	 * 所在类名：InterviewAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-20 下午04:10:51
	 *
	 *
	 * @return
	 */
	public String modifyForward(){
		try {
			Interview dbinterview = interviewService.get(Interview.class, id);
			getValueStack().setValue("interview", dbinterview);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			Log.debug(BrandAction.class, "The brand is not exist!");
			getValueStack().set("msg", "the object is not exist in DataBase, then cannot forward to modify page!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		String oldPosterurl = interview.getPoster();
		String oldVideourl = interview.getInterviewurl();;
		String posterurl = "";
		String videourl = "";
		boolean isUpdatePoster = false;
		boolean isUpdateVideo = false;
		try{
			if(videoFileFileName != null && !"".equals(videoFileFileName)){
				String videoFileName = new Date().getTime()+getExtention(videoFileFileName);
				videourl = "upload/interview"+"/"+interview.getInterviewtype()+"/"+videoFileName;
				interview.setInterviewurl(getWebRootPath()+videourl);
				isUpdateVideo = true;
			}
			if(posterFileFileName != null && !"".equals(posterFileFileName)){
				String posterFileName = new Date().getTime()+getExtention(posterFileFileName);
				posterurl = "upload/interview"+"/"+interview.getInterviewtype()+"/"+posterFileName;
				interview.setPoster(getWebRootPath()+posterurl);
				isUpdatePoster = true;
			}
			if(designer != null && !"".equals(designer.getEname())){
				designer = designerService.searchByName(designer.getEname());
				if(designer != null){
					interview.setDesigner(designer);
					designer.getInterviews().add(interview);
				}
			}
			interview.setModifiedTime(new Date());
			interview.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			boolean isSuccess = interviewService.updateInterviewAttachInfo(interview, isUpdatePoster, isUpdateVideo);
			if(!isSuccess){
				throw new ServiceException();
			}
			if(isUpdatePoster){
				if(oldPosterurl != null){
					String absoluteUrl = getBasePath()+oldPosterurl;
					File oldFile = new File(absoluteUrl);
					if(oldFile.exists()){
						oldFile.delete();
					}
				}
				String absoluteUrl = getBasePath()+posterurl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(posterFile,destFile);
			}
			if(isUpdateVideo){
				if(oldVideourl != null){
					String absoluteUrl = getBasePath()+oldVideourl;
					File oldFile = new File(absoluteUrl);
					if(oldFile.exists()){
						oldFile.delete();
					}
				}
				String absoluteUrl = getBasePath()+videourl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(videoFile,destFile);
			}
			getValueStack().set("msg", "Object save success,update interview ["+interview.getInterviewEname()+"] successfully!");
			return "modify";
		}catch(ServiceException e1){
			e1.printStackTrace();
			Log.debug(InterviewAction.class, e1.getMessage());
			getValueStack().set("msg", "Object save error,update interview ["+interview.getInterviewEname()+"] failure!");
			return "modify";
		}catch(UploadFileException e2){
			e2.printStackTrace();
			Log.debug(InterviewAction.class, e2.getMessage());
			getValueStack().set("msg", "Object save success,but attactments upload failure!");
			return "modify";
		}
		catch(Exception e){
			e.printStackTrace();
			Log.debug(InterviewAction.class, e.getMessage());
			getValueStack().set("msg", "update interview ["+interview.getInterviewEname()+"] failure!");
			return "modify";
		}
	}
	
	public String showInterviews(){
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 

		try {
			List<Interview> interviews = interviewService.getInterviewsByDesingerAndType(designer.getDesignerId(), type);
			String basePath = getBasePath();
			XmlParse parse = new InterviewPlayList(interviews);
			String outputfile = basePath+"frontend/menus/designer/playlist.xml";
			parse.generateXMLFile(outputfile);
			Interview i = interviews.get(0);
			InterviewVO ivo = new InterviewVO(i);
			ivo.process(language);
			getValueStack().set("interviewVO", ivo);
		} catch (ServiceException e) {
			e.printStackTrace();
		} 
		return "load_interview_success";
	}
	
	
	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
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

	public File getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(File videoFile) {
		this.videoFile = videoFile;
	}

	public String getVideoFileContentType() {
		return videoFileContentType;
	}

	public void setVideoFileContentType(String videoFileContentType) {
		this.videoFileContentType = videoFileContentType;
	}

	public String getVideoFileFileName() {
		return videoFileFileName;
	}

	public void setVideoFileFileName(String videoFileFileName) {
		this.videoFileFileName = videoFileFileName;
	}

	public File getPosterFile() {
		return posterFile;
	}

	public void setPosterFile(File posterFile) {
		this.posterFile = posterFile;
	}

	public String getPosterFileContentType() {
		return posterFileContentType;
	}

	public void setPosterFileContentType(String posterFileContentType) {
		this.posterFileContentType = posterFileContentType;
	}

	public String getPosterFileFileName() {
		return posterFileFileName;
	}

	public void setPosterFileFileName(String posterFileFileName) {
		this.posterFileFileName = posterFileFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
