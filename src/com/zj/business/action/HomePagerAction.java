package com.zj.business.action;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ckfinder.connector.utils.ImageUtils;
import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.observer.LanguageType;
import com.zj.business.po.Account;
import com.zj.business.po.Designer;
import com.zj.business.po.HomePager;
import com.zj.business.po.Lookbook;
import com.zj.business.po.News;
import com.zj.business.service.IAccountService;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IHomePagerService;
import com.zj.business.service.ILookbookService;
import com.zj.business.vo.DesignerVO;
import com.zj.business.vo.HomepagerVO;
import com.zj.business.vo.LookbookVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.cache.EHCacheService;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.FileUtil;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;

@Component("homepagerAction")
@Scope("prototype")
public class HomePagerAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8174360054063433686L;
	private static final Logger log = Logger.getLogger(HomePagerAction.class);
	
	private List<Designer> designerlist;
	private Long featuredDesignerId;
	private String accountname;
	private List<News> newslist;
	private List<Lookbook> lookbooklist;
	
	@Resource
	private IAccountService accountService;
	@Resource
	private IHomePagerService homepagerService;
	@Resource
	private IDesignerService designerService;
	@Resource
	private ILookbookService lookbookService;
	@Resource
	private EHCacheService ehCacheService;
	@Value("#{envConfig['upload.homepage.dir']}")
	private String fileUploadPath;
	
	
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
	
	public String modify(){
		try {
			HomePager homepager = ehCacheService.getHomepagerCache().get(1L);
			String oldVideoUrl = homepager.getVideoUrl();
			String imageDir = homepager.getImageDir();
			if(StringUtil.isEmpty(imageDir)){
				homepager.setImageDir(fileUploadPath);
			}
			boolean hasVideoFile = false;
			if(videoFile != null){
				hasVideoFile = true;
				homepager.setVideoUrl(fileUploadPath+"video/"+videoFileFileName);
			}
			//featured Designer
			if(featuredDesignerId != null && featuredDesignerId !=0){
				homepager.setFeaturedDesigner(featuredDesignerId);
			}
			//designer
			if(designerlist != null && !designerlist.isEmpty()){
				Set<Designer> designers = new HashSet<Designer>();
				for(int i=0;i<designerlist.size();i++){
					Designer designer = designerlist.get(i);
					designer = designerService.get(Designer.class, designer.getDesignerId());
					designers.add(designer);
				}
				homepager.setDesigners(designers);
			}
			//what's news
//			if(newslist != null && !newslist.isEmpty()){
//				Set<News> newses = new HashSet<News>();
//				for(int i=0;i<newslist.size();i++){
//					News news = newslist.get(i);
//					news = newsService.get(News.class,news.getNewsId());
//					newses.add(news);
//				}
//				homepager.setNews(newses);
//			}
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
			
			String dirPath = getBasePath()+imageDir;
			if(imageFiles != null){
				preDeleteDirectory(new File(dirPath));
				for(int i=0;i<imageFiles.length;i++){
					String imageName = UUID.randomUUID().toString();
					String fileType = getExtention(imageFilesFileName[i]);
					String imageFilePath = dirPath+imageName+fileType;
					String thumbnailPath = dirPath+imageName+CommonConstant.ThumbnailSuffix+fileType;
					File destFile = new File(imageFilePath);
					File destThumbnail = new File(thumbnailPath);
					copyByChannel(imageFiles[i],destFile);
					ImageUtils.createResizedImage(imageFiles[i], destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
					
				}
			}
			
			if(hasVideoFile){
				String absolutePath = getBasePath()+homepager.getVideoUrl();
				File video = new File(absolutePath);
				if(!video.getParentFile().exists()){
					video.getParentFile().mkdirs();
				}
				String oldVideoAbsolutePath = getBasePath()+oldVideoUrl;
				preDeleteFile(oldVideoAbsolutePath);
				copyByChannel(videoFile, video);
			}
			getValueStack().set("msg","Modify Homepage information Success!");
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug(se);
			getValueStack().set("msg","Modify Homepage information error!");
		}catch(UploadFileException ue){
			ue.printStackTrace();
			log.debug("upload attachments error!",ue);
			getValueStack().set("msg","Modify Homepage information error!");
		}
		catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg","Modify Homepage information error!");
		}
		return "modify";
	}

	public String loadData(){
		try {
			LanguageType type = getLanguageType();
	        Language language = Language.getInstance();
			if(accountname != null){
				Account account = accountService.getAccount(accountname);
				session.put(GlobalParam.LOGIN_ACCOUNT_SESSION, account);
			}
			HomePager homepager = homepagerService.get(HomePager.class, 1L);
			//load designer
			Set<Designer> designers = homepager.getDesigners();
			List<DesignerVO> designerVOs = new LinkedList<DesignerVO>();
			Iterator<Designer> iter = designers.iterator();
			while(iter.hasNext()){
				Designer d = iter.next();
				DesignerVO dvo = VOFactory.getObserverVO(DesignerVO.class, d);
				designerVOs.add(dvo);
				language.addObserver(dvo);
			}
			//load featured designer
			DesignerVO featuredDesignerVO = null;
			if(homepager.getFeaturedDesigner() != null && homepager.getFeaturedDesigner() != 0){
				Designer featuredDesigner = designerService.get(Designer.class, homepager.getFeaturedDesigner());
				featuredDesignerVO = VOFactory.getObserverVO(DesignerVO.class, featuredDesigner);
				language.addObserver(featuredDesignerVO);
			}
			
			//load images
			List<String> imageUrls = new LinkedList<String>();
			String imageDirPath = homepager.getImageDir();
			String basePath = getBasePath();
			if(imageDirPath != null && !"".equals(imageDirPath)){
				String absoluteDirPath = basePath + imageDirPath;
				File imageDir = FileUtil.generateDirectory(absoluteDirPath);
				Collection<File> UnThumbnails = FileUtil.listFiles(imageDir, FileFilterUtils.asFileFilter(new FilenameFilter() {
									
									@Override
									public boolean accept(File dir, String name) {
										if(name.indexOf(CommonConstant.ThumbnailSuffix) == -1){
											return true;
										}
										return false;
									}
								}),null);
				for(File unThumbnail:UnThumbnails){
					String thumbnailPath = unThumbnail.getAbsolutePath();
					String surfixPath = thumbnailPath.substring(basePath.length());
					surfixPath = StringUtil.replaceChars(surfixPath, '\\', '/');
					imageUrls.add(surfixPath);
				}
			}else{
				String absoluteDirPath = basePath + fileUploadPath;
				FileUtil.generateDirectory(absoluteDirPath);
			}
			
			//load collection
			Set<Lookbook> lookbooks = homepager.getLookbooks();
			List<LookbookVO> lookbookVOs = new ArrayList<LookbookVO>();
			Iterator<Lookbook> lookbookkey = lookbooks.iterator();
			while(lookbookkey.hasNext()){
				Lookbook po = lookbookkey.next();
				LookbookVO vo = new LookbookVO(po,getBasePath());
				lookbookVOs.add(vo);
				language.addObserver(vo);
			}
			String videourl = homepager.getVideoUrl();
			language.setLanguage(type);
			HomepagerVO vo = new HomepagerVO(featuredDesignerVO, designerVOs, lookbookVOs, videourl, imageUrls);
			getValueStack().set("homevo", vo);
		} catch (ServiceException se) {
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "load_success";
	}

	public String loadDataForBackend(){
		try {
			HomePager homepager = homepagerService.get(HomePager.class, 1L);
			HomepagerVO vo = VOFactory.getObserverVO(HomepagerVO.class);
			//load featured designer
			if(homepager.getFeaturedDesigner() != null){
				Designer featuredDesigner = designerService.get(Designer.class, homepager.getFeaturedDesigner());
				vo.setFeaturedDesigner(featuredDesigner);
			}
			//load home thumbnail images
			String imageDirPath = getBasePath()+homepager.getImageDir();
			File imageDir = new File(imageDirPath);
			if(imageDir.exists()){
				Collection<File> thumbnails = FileUtil.listFiles(imageDir, FileFilterUtils.asFileFilter(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						if(name.matches(".+("+CommonConstant.ThumbnailSuffix+").+")){
							return true;
						}
						return false;
					}
				}),null);
				List<String> imageUrls = new LinkedList<String>();
				String basePath = getBasePath();
				for(File thumbnail:thumbnails){
					String thumbnailPath = thumbnail.getAbsolutePath();
					String surfixPath = thumbnailPath.substring(basePath.length());
					surfixPath = StringUtil.replaceChars(surfixPath, '\\', '/');
					imageUrls.add(surfixPath);
				}
				vo.setImageThumbnailUrls(imageUrls);
			}
			//load video
			String videoUrl = homepager.getVideoUrl();
			if(!StringUtil.isEmpty(videoUrl)){
				String videoName = videoUrl.substring(videoUrl.lastIndexOf("/")+1);
				vo.setVideoName(videoName);
			}
			//load video poster thumbnail
			String posterUrl = homepager.getPoster();
			if(!StringUtil.isEmpty(homepager.getPoster())){
				StringBuffer sb = new StringBuffer(posterUrl);
	            sb.insert(posterUrl.lastIndexOf("."), "_poster"+CommonConstant.ThumbnailSuffix);
				String posterThumbnailUrl = sb.toString();
				vo.setPosterThumbnailUrl(posterThumbnailUrl);
			}
			Set<Designer> designers = homepager.getDesigners();
			if(designers != null && !designers.isEmpty()){
				vo.setDesigners(Arrays.asList(designers.toArray(new Designer[0])));
			}
			Set<Lookbook> lookbooks = homepager.getLookbooks();
			if(lookbooks != null && !lookbooks.isEmpty()){
				vo.setLookbooks(Arrays.asList(lookbooks.toArray(new Lookbook[0])));
			}
			
			getValueStack().set("homevo", vo);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "load_success";
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

	public Long getFeaturedDesignerId() {
		return featuredDesignerId;
	}

	public void setFeaturedDesignerId(Long featuredDesignerId) {
		this.featuredDesignerId = featuredDesignerId;
	}
	public static void main(String[] args){
		String str = "test_thumbnail.jpg";
		System.out.println(str.matches("[^(_thumbnail]"));
	}
}
