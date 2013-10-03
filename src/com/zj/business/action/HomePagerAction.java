package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Account;
import com.zj.business.po.Designer;
import com.zj.business.po.HomePager;
import com.zj.business.po.Lookbook;
import com.zj.business.po.News;
import com.zj.business.service.IAccountService;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IHomePagerService;
import com.zj.business.service.ILookbookService;
import com.zj.business.service.INewsSerivce;
import com.zj.common.cache.EHCacheService;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.log.Log;
import com.zj.core.control.struts.BaseAction;

@Component("homepagerAction")
@Scope("prototype")
public class HomePagerAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8174360054063433686L;
	
	private List<Designer> designerlist;
	private Designer featuredDesigner;
	private String accountname;
	private List<News> newslist;
	private String videoUrl;
	private String imageDir;
	private List<Lookbook> lookbooklist;
	
	@Resource
	private IAccountService accountService;
	@Resource
	private IHomePagerService homepagerService;
	@Resource
	private IDesignerService designerService;
	@Resource
	private INewsSerivce newsService;
	@Resource
	private ILookbookService lookbookService;
	@Resource
	private EHCacheService ehCacheService;
	
	//multiple file upload
	private File[] imageFiles;
	private String[] imageFilesContentType;
	private String[] imageFilesFileName;
	
	private File videoFile;
	private String videoFileContentType;
	private String videoFileFileName;
	
	private File poster;
	private String posterContentType;
	private String posterFileName;
	
	private Locale locale = null;
	private String language = "en";
	
	public String modify(){
		try {
//			HomePager homepager = homepagerService.get(HomePager.class, 1L);
			HomePager homepager = ehCacheService.getHomepagerCache().get(1L);
			boolean hasImageFile = false;
			if(imageFiles != null && imageFiles.length > 0){
				hasImageFile = true;
				homepager.setImageDir("upload/homepage/");
			}
			boolean hasVideoFile = false;
			if(videoFile != null){
				hasVideoFile = true;
				String videoName = new Date().getTime()+getExtention(videoFileFileName);
				homepager.setVideoUrl("upload/homepage/video/"+videoName);
			}
			boolean hasPoster = false;
			if(poster != null){
				hasPoster = true;
				String posterName = new Date().getTime()+getExtention(posterFileName);
				homepager.setPoster("upload/homepage/video/"+posterName);
			}
			
			//featured Designer
			if(featuredDesigner != null && !"".equals(featuredDesigner.getEname())){
				featuredDesigner = designerService.searchByName(featuredDesigner.getEname());
				homepager.setFeaturedDesigner(featuredDesigner.getDesignerId());
			}
			//designer
			if(designerlist != null && !designerlist.isEmpty()){
				Set<Designer> designers = new HashSet<Designer>();
				for(int i=0;i<designerlist.size();i++){
					Designer designer = designerlist.get(i);
					if(i<3){
						designer = designerService.get(Designer.class, designer.getDesignerId());
						designers.add(designer);
					}
				}
				homepager.setDesigners(designers);
			}
			//what's news
			if(newslist != null && !newslist.isEmpty()){
				Set<News> newses = new HashSet<News>();
				for(int i=0;i<newslist.size();i++){
					News news = newslist.get(i);
					news = newsService.get(News.class,news.getNewsId());
					newses.add(news);
				}
				homepager.setNews(newses);
			}
			//lookbook
			if(lookbooklist != null && !lookbooklist.isEmpty()){
				Set<Lookbook> lookbooks = new HashSet<Lookbook>();
				for(int i=0;i<lookbooklist.size();i++){
					Lookbook lookbook = lookbooklist.get(i);
					lookbook = lookbookService.get(Lookbook.class,lookbook.getLookbookid());
					lookbooks.add(lookbook);
				}
				homepager.setLookbooks(lookbooks);
			}
			homepagerService.update(homepager);
			
			ehCacheService.getHomepagerCache().put(1L, homepager);
			
			if(hasImageFile){
				String dirPath = getBasePath()+homepager.getImageDir();
				File dir = new File(dirPath);
				if(!dir.exists()){
					dir.mkdirs();
				}
				File[] listFile = dir.listFiles();
				if(listFile != null && listFile.length > 0){
					for(File file:listFile){
						file.delete();
					}
				}
				for(int i=0;i<imageFiles.length;i++){
					String imageFilePath = dirPath+"/"+(i+1)+getExtention(imageFilesFileName[i]);
					File destFile = new File(imageFilePath);
					copy(imageFiles[i],destFile);
				}
			}
			
			if(hasVideoFile){
				String absolutePath = getBasePath()+homepager.getVideoUrl();
				File video = new File(absolutePath);
				File parentDir = video.getParentFile();
				if(!parentDir.exists()){
					parentDir.mkdirs();
				}
				File[] listFile = parentDir.listFiles();
				if(listFile != null &&listFile.length > 0){
					for(File file:listFile){
						file.delete();
					}
				}
				copyByChannel(videoFile, video);
			}
			if(hasPoster){
				String absolutePath = getBasePath()+homepager.getPoster();
				File posterFile = new File(absolutePath);
				File parentDir = posterFile.getParentFile();
				if(!parentDir.exists()){
					parentDir.mkdirs();
				}
				copyByChannel(poster, posterFile);
			}
			getValueStack().set("msg","Modify Homepage information Success!");
			return "modify";
		}catch(ServiceException se){
			se.printStackTrace();
			Log.debug(HomePagerAction.class, se.getMessage());
			getValueStack().set("msg","Modify Homepage information error!");
			return "modify";
		}catch(UploadFileException ue){
			ue.printStackTrace();
			Log.debug(HomePagerAction.class, ue.getMessage());
			getValueStack().set("msg","Modify Homepage information error!");
			return "modify";
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.debug(HomePagerAction.class, e.getMessage());
			getValueStack().set("msg","Modify Homepage information error!");
			return "modify";
		}
	}

	public String loadData(){
		try {
			Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
			if (sessionLocale != null && sessionLocale instanceof Locale) {
                locale = (Locale) sessionLocale;
                language = locale.getLanguage()+"_"+locale.getCountry();
            }
			if(accountname != null){
				Account account = accountService.getAccount(accountname);
				session.put(GlobalParam.LOGIN_ACCOUNT_SESSION, account);
			}
			HomePager homepager = homepagerService.get(HomePager.class, 1L);
			//load designer
//			Set<Designer> designers = homepager.getDesigners();
//			List<DesignerVO> designerVOs = new ArrayList<DesignerVO>();
//			Iterator<Designer> iter = designers.iterator();
//			while(iter.hasNext()){
//				Designer d = iter.next();
//				DesignerVO vo = new DesignerVO(d);
//				vo.process(language);
//				designerVOs.add(vo);
//			}
//			getValueStack().set("designerlist", designerVOs);
			//load featured designer
//			if(homepager.getFeaturedDesigner() != null){
//				Designer featuredDesigner = designerService.get(Designer.class, homepager.getFeaturedDesigner());
//				DesignerVO designerVo = new DesignerVO(featuredDesigner);
//				designerVo.process(language);
//				getValueStack().set("featuredDesigner", designerVo);
//			}
			
			//load image
			String imageDirPath = getBasePath()+homepager.getImageDir();
			if(imageDirPath != null && !"".equals(imageDirPath)){
				File imageDir = new File(imageDirPath);
				File[] listFile = imageDir.listFiles();
				List<String> imagesrcs = new ArrayList<String>();
				if(listFile != null){
					for(File file:listFile){
						if(file.isFile()){
							String absolutePath = file.getAbsolutePath();
							String imagePath = absolutePath.substring(getBasePath().length()).replace("\\", "/");
							imagesrcs.add(imagePath);
						}
					}
				}
				getValueStack().set("imagesrcs", imagesrcs);
			}
			
			//load news
//			Set<News> news = homepager.getNews();
//			List<NewsVO> newslist = new ArrayList<NewsVO>(); 
//			Iterator<News> newskey = news.iterator();
//			while(newskey.hasNext()){
//				News n = newskey.next();
//				NewsVO nvo = new NewsVO(n,getBasePath());
//				nvo.process(language);
//				newslist.add(nvo);
//			}
//			getValueStack().set("newslist", newslist);
			
			//load collection
//			Set<Lookbook> lookbooks = homepager.getLookbooks();
//			List<LookbookVO> lookbookvos = new ArrayList<LookbookVO>();
//			Iterator<Lookbook> lookbookkey = lookbooks.iterator();
//			while(lookbookkey.hasNext()){
//				Lookbook po = lookbookkey.next();
//				LookbookVO vo = new LookbookVO(po,getBasePath());
//				vo.process(language);
//				lookbookvos.add(vo);
//			}
//			getValueStack().set("lookbooklist", lookbookvos);
			
			//load video
			getValueStack().set("videourl", homepager.getVideoUrl());
			//load poster
			getValueStack().set("poster", homepager.getPoster());
			
		} catch (ServiceException se) {
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "load_success";
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public Designer getFeaturedDesigner() {
		return featuredDesigner;
	}

	public void setFeaturedDesigner(Designer featuredDesigner) {
		this.featuredDesigner = featuredDesigner;
	}

	public List<Designer> getDesignerlist() {
		return designerlist;
	}

	public void setDesignerlist(List<Designer> designerlist) {
		this.designerlist = designerlist;
	}

	public List<News> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}

	public List<Lookbook> getLookbooklist() {
		return lookbooklist;
	}

	public void setLookbooklist(List<Lookbook> lookbooklist) {
		this.lookbooklist = lookbooklist;
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

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public File getPoster() {
		return poster;
	}

	public void setPoster(File poster) {
		this.poster = poster;
	}

	public String getPosterContentType() {
		return posterContentType;
	}

	public void setPosterContentType(String posterContentType) {
		this.posterContentType = posterContentType;
	}

	public String getPosterFileName() {
		return posterFileName;
	}

	public void setPosterFileName(String posterFileName) {
		this.posterFileName = posterFileName;
	}

}
