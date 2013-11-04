package com.zj.business.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ckfinder.connector.utils.ImageUtils;
import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.playlist.InterviewPlayList;
import com.zj.business.po.Designer;
import com.zj.business.po.Interview;
import com.zj.business.service.IInterviewService;
import com.zj.business.vo.InterviewVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
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
	private static final Logger log = Logger.getLogger(RunwayshowAction.class);
	private Interview interview;
	private Designer designer;
	@Resource
	private IInterviewService interviewService;
	@Value("#{envConfig['upload.interview.dir']}")
	private String fileUploadPath;
	
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
		String posterThumbnailUrl = "";
		boolean isAddVideo = false;
		boolean isAddPoster = false;
		try{
			if(videoFile != null){
				isAddVideo = true;
				String fileType = getExtention(videoFileFileName);
				String videoName = UUID.randomUUID().toString();
				videourl = fileUploadPath+interview.getInterviewtype()+File.separator+videoName+fileType;
				interview.setInterviewurl(getWebRootPath()+videourl);
			}
			if(posterFile != null){
				isAddPoster = true;
				String fileType = getExtention(posterFileFileName);
				String posterFileName =UUID.randomUUID().toString();
				posterurl = fileUploadPath+interview.getInterviewtype()+File.separator+posterFileName+fileType;
				posterThumbnailUrl = fileUploadPath+interview.getInterviewtype()+File.separator+posterFileName+CommonConstant.ThumbnailSuffix+ fileType;
				interview.setPoster(getWebRootPath()+posterurl);
			}
			interview.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			interview.setCreateTime(new Date());
			interviewService.save(interview, designer);
			if(isAddPoster){
				String absoluteUrl = getBasePath()+posterurl;
				String absoluteThumbnailUrl = getBasePath() +posterThumbnailUrl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(posterFile,destFile);
				ImageUtils.createResizedImage(posterFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
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
			log.debug("create interview error!",se);
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Failure, root cause : "+se.getMessage());
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("upload attachments error!",ue);
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] success, but upload attachments occured error! ");
			return "save_failure";
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "create Interview ["+interview.getInterviewEname()+"] Failed, root cause : "+e.getMessage());
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
			log.debug(e);
			sendJSONdata(json);
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
			InterviewVO vo = VOFactory.getObserverVO(InterviewVO.class, dbinterview);
			getValueStack().set("interviewvo", vo);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The interview is not exist!");
			getValueStack().set("msg", "the object is not exist in DataBase, then cannot forward to modify page!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		int startIndx = getWebRootPath().length();
		String oldPosterurl = interview.getPoster();
		if(!StringUtil.isEmpty(oldPosterurl)){
			oldPosterurl = oldPosterurl.substring(startIndx);
		}
		String oldVideourl = interview.getInterviewurl();
		if(!StringUtil.isEmpty(oldVideourl)){
			oldVideourl = oldVideourl.substring(startIndx);
		}
		String posterurl = "";
		String videourl = "";
		String posterThumbnailUrl = "";
		boolean isUpdatePoster = false;
		boolean isUpdateVideo = false;
		try{
			if(videoFile != null){
				String fileType = getExtention(videoFileFileName);
				String videoFileName = UUID.randomUUID().toString();
				videourl = fileUploadPath+interview.getInterviewtype()+"/"+videoFileName+fileType;
				interview.setInterviewurl(getWebRootPath()+videourl);
				isUpdateVideo = true;
			}
			if(posterFile != null){
				String fileType = getExtention(posterFileFileName);
				String posterFileName =  UUID.randomUUID().toString();
				posterurl = fileUploadPath+interview.getInterviewtype()+"/"+posterFileName+fileType;
				posterThumbnailUrl = fileUploadPath+interview.getInterviewtype()+"/"+posterFileName+CommonConstant.ThumbnailSuffix+ fileType;
				interview.setPoster(getWebRootPath()+posterurl);
				isUpdatePoster = true;
			}
			interview.setModifiedTime(new Date());
			interview.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			interviewService.update(interview,designer);
			if(isUpdatePoster){
				preDeleteFile(getBasePath()+oldPosterurl);
				String absoluteUrl = getBasePath()+posterurl;
				String absoluteThumbnailUrl = getBasePath()+posterThumbnailUrl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(posterFile,destFile);
				ImageUtils.createResizedImage(posterFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			}
			if(isUpdateVideo){
				preDeleteFile(getBasePath()+oldVideourl);
				String absoluteUrl = getBasePath()+videourl;
				File destFile = new File(absoluteUrl);
				if(!destFile.getParentFile().exists()){
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(videoFile,destFile);
			}
			getValueStack().set("msg", "update interview ["+interview.getInterviewEname()+"] successfully!");
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("update interview error!", se);
			getValueStack().set("msg", "update interview ["+interview.getInterviewEname()+"] failure, root cause : "+se.getMessage());
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("upload attachments error!",ue);
			getValueStack().set("msg", "update interview ["+interview.getInterviewEname()+"] success, but upload attachments occured error !");
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "update interview ["+interview.getInterviewEname()+"] failed, because of time session out, please relogin!");
		}
		return "modify";
	}
	
	public String showInterviews(){
		Language language = Language.getInstance();
		try {
			List<Interview> interviews = interviewService.getInterviewsByDesingerAndType(designer.getDesignerId(), type);
			String basePath = getBasePath();
			XmlParse parse = new InterviewPlayList(interviews);
			String outputfile = basePath+"frontend"+File.separator+"menus"+File.separator+"designer"+File.separator+designer.getDesignerId()+File.separator+"playlist.xml";
			parse.generateXMLFile(outputfile);
			Interview i = interviews.get(0);
			InterviewVO ivo = VOFactory.getObserverVO(InterviewVO.class, i);
			language.setLanguage(getLanguageType());
			getValueStack().set("interviewvo", ivo);
			getValueStack().set("designerId", designer.getDesignerId());
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
