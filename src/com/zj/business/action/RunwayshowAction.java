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
import com.zj.business.playlist.RunwayshowPlayList;
import com.zj.business.po.Brand;
import com.zj.business.po.Runwayshow;
import com.zj.business.service.IRunwayshowService;
import com.zj.business.vo.RunwayshowVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.common.utils.XmlParse;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("runwayshowAction")
@Scope("prototype")
public class RunwayshowAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4694223633325668341L;
	private static final Logger log = Logger.getLogger(RunwayshowAction.class);
	private Runwayshow runwayshow;
	private Brand brand;
	@Resource
	private IRunwayshowService runwayshowService;
	@Value("#{envConfig['upload.runwayshow.dir']}")
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
				videourl = fileUploadPath+videoName+fileType;
				runwayshow.setRunwayshowUrl(getWebRootPath()+videourl);
			}
			if(posterFile != null){
				isAddPoster = true;
				String fileType = getExtention(posterFileFileName);
				String posterFileName =UUID.randomUUID().toString();
				posterurl = fileUploadPath+posterFileName+fileType;
				posterThumbnailUrl = fileUploadPath+posterFileName+CommonConstant.ThumbnailSuffix+ fileType;
				runwayshow.setPoster(getWebRootPath()+posterurl);
			}
			runwayshow.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			runwayshow.setCreateTime(new Date());
			runwayshowService.save(runwayshow,brand);
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
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Successfully! ");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("create runwayshow error!",se);
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Failure, root cause : "+se.getMessage());
			return "save_failure";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("uploade attachemnts error!",ue);
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Success, but upload attachments occured error! ");
			return "save_failure";
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Failed, root cause is "+e.getMessage());
			return "save_failure";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<Runwayshow> pageinfo = runwayshowService.loadRunwayshowsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			runwayshowService.bulkDelete(Runwayshow.class, keys);
			String msg = "Delete[" + keys.length + "] Runway Shows Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Runway Shows Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug(e);
			sendJSONdata(json);
		}
	}

	public void doSearch(){
		try {
			PageInfo<Runwayshow> pageinfo = runwayshowService.searchList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：forward to modify page after accquirred data
	 *
	 * 方法名称：modifyForward
	 *
	 * 所在类名：RunwayshowAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-21 下午05:13:37
	 *
	 *
	 * @return
	 */
	public String modifyForward(){
		try {
			Runwayshow dbrunwayshow = runwayshowService.get(Runwayshow.class, id);
			RunwayshowVO vo = VOFactory.getObserverVO(RunwayshowVO.class,dbrunwayshow);
			getValueStack().set("runwayshowvo", vo);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The runwayshow is not exist!",e);
			getValueStack().set("msg", "the object is not exist in DataBase, then cannot forward to modify page!");
			return "modify_forward_failure";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String update(){
		int startIndx = getWebRootPath().length();
		String oldPosterurl = runwayshow.getPoster();
		if(!StringUtil.isEmpty(oldPosterurl)){
			oldPosterurl = oldPosterurl.substring(startIndx);
		}
		String oldVideourl = runwayshow.getRunwayshowUrl();
		if(!StringUtil.isEmpty(oldVideourl)){
			oldVideourl = oldVideourl.substring(startIndx);
		}
		String posterurl = "";
		String videourl = "";
		String posterThumbnailUrl = "";
		boolean isUpdatePoster = false;
		boolean isUpdateVideo = false;
		try{
			if(videoFile != null ){
				String fileType = getExtention(videoFileFileName);
				String videoFileName = UUID.randomUUID().toString();
				videourl = fileUploadPath+videoFileName+fileType;
				runwayshow.setRunwayshowUrl(getWebRootPath()+videourl);
				isUpdateVideo = true;
			}
			if(posterFile != null){
				String fileType = getExtention(posterFileFileName);
				String posterFileName =  UUID.randomUUID().toString();
				posterurl = fileUploadPath+posterFileName+fileType;
				posterThumbnailUrl = fileUploadPath+posterFileName+CommonConstant.ThumbnailSuffix+ fileType;
				runwayshow.setPoster(getWebRootPath()+posterurl);
				isUpdatePoster = true;
			}
			runwayshow.setModifiedTime(new Date());
			runwayshow.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			runwayshowService.update(runwayshow,brand);
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
			getValueStack().set("msg", "update runwayshow ["+runwayshow.getRunwayshowEname()+"] successfully!");
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("update runwayshow error!",se);
			getValueStack().set("msg", "update runwayshow ["+runwayshow.getRunwayshowEname()+"] failure, root cause : "+se.getMessage());
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("upload attachments error!", ue);
			getValueStack().set("msg", "update runwayshow [ "+runwayshow.getRunwayshowEname()+"] success,but upload attachments occured error!");
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg", "update runwayshow ["+runwayshow.getRunwayshowEname()+"] failed,because of session time out , please relogin!");
		}
		return "modify";
	}
	
	//below methods for frontend
	public String showByBrand(){
		Language language = Language.getInstance();
		try{
			List<Runwayshow> runwayshows = runwayshowService.getRunwayShowByBrand(brand.getBrandid());
			String basePath = getBasePath();
			XmlParse parse = new RunwayshowPlayList(runwayshows);
			String outputfile = basePath+"frontend"+File.separator+"menus"+File.separator+"brand"+File.separator+"playlist.xml";
			parse.generateXMLFile(outputfile);
			Runwayshow show = runwayshows.get(0);
			RunwayshowVO rvo = VOFactory.getObserverVO(RunwayshowVO.class, show);
			language.setLanguage(getLanguageType());
			getValueStack().set("runwayshowvo", rvo);
		} catch (ServiceException e) {
			e.printStackTrace();
		} 
		return "load_runwayshow_success";
	}
	
	public Runwayshow getRunwayshow() {
		return runwayshow;
	}

	public void setRunwayshow(Runwayshow runwayshow) {
		this.runwayshow = runwayshow;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
