package com.zj.business.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.playlist.RunwayshowPlayList;
import com.zj.business.po.Brand;
import com.zj.business.po.Runwayshow;
import com.zj.business.service.IRunwayshowService;
import com.zj.business.vo.RunwayshowVO;
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
		boolean isAddVideo = false;
		boolean isAddPoster = false;
		try{
			if(videoFileFileName != null && !"".equals(videoFileFileName)){
				isAddVideo = true;
				String videoFileName = new Date().getTime()+getExtention(videoFileFileName);
				videourl = "upload/runwayshow/video"+"/"+videoFileName;
				runwayshow.setRunwayshowUrl(getWebRootPath()+videourl);
			}
			if(posterFileFileName != null && !"".equals(posterFileFileName)){
				isAddPoster = true;
				String posterFileName = new Date().getTime()+getExtention(posterFileFileName);
				posterurl = "upload/runwayshow/video"+"/"+posterFileName;
				runwayshow.setPoster(getWebRootPath()+posterurl);
			}
			runwayshow.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			runwayshow.setCreateTime(new Date());
			runwayshowService.save(runwayshow,brand);
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
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Successfully! ");
			return "save_success";
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug("create runwayshow error!",se);
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Failure! ");
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
			getValueStack().set("msg", "create RunwayShow ["+runwayshow.getRunwayshowEname()+"] Failed, because of session time out ,please relogin! ");
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
			getValueStack().setValue("runwayshow", dbrunwayshow);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The brand is not exist!",e);
			getValueStack().set("msg", "the object is not exist in DataBase, then cannot forward to modify page!");
			return "modify_forward_failure";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String update(){
		int prefixLen = getWebRootPath().length();
		String oldPosterurl = runwayshow.getPoster();
		String oldVideourl = runwayshow.getRunwayshowUrl();
		String posterurl = "";
		String videourl = "";
		boolean isUpdatePoster = false;
		boolean isUpdateVideo = false;
		try{
			if(videoFileFileName != null && !"".equals(videoFileFileName)){
				String videoFileName = new Date().getTime()+getExtention(videoFileFileName);
				videourl = "upload/runwayshow/video"+"/"+videoFileName;
				runwayshow.setRunwayshowUrl(getWebRootPath()+videourl);
				isUpdateVideo = true;
			}
			if(posterFileFileName != null && !"".equals(posterFileFileName)){
				String posterFileName = new Date().getTime()+getExtention(posterFileFileName);
				posterurl = "upload/runwayshow/video"+"/"+posterFileName;
				runwayshow.setPoster(getWebRootPath()+posterurl);
				isUpdatePoster = true;
			}
			runwayshow.setModifiedTime(new Date());
			runwayshow.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			runwayshowService.update(runwayshow,brand);
			if(isUpdatePoster){
				if(oldPosterurl != null){
					oldPosterurl = oldPosterurl.substring(prefixLen);
					String absoluteUrl = getBasePath()+oldPosterurl;
					preDeleteFile(absoluteUrl);
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
					oldVideourl = oldVideourl.substring(prefixLen);
					String absoluteUrl = getBasePath()+oldVideourl;
					preDeleteFile(absoluteUrl);
				}
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
			getValueStack().set("msg", "update runwayshow ["+runwayshow.getRunwayshowEname()+"] failure!");
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
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            language = locale.getLanguage()+"_"+locale.getCountry();
        } 
		try{
			List<Runwayshow> runwayshows = runwayshowService.getRunwayShowByBrand(brand.getBrandid());
			String basePath = getBasePath();
			XmlParse parse = new RunwayshowPlayList(runwayshows);
			String outputfile = basePath+"frontend/menus/brand/playlist.xml";
			parse.generateXMLFile(outputfile);
			Runwayshow show = runwayshows.get(0);
			RunwayshowVO rvo = new RunwayshowVO(show);
//			rvo.process(language);
			getValueStack().set("runwayshowVO", rvo);
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
