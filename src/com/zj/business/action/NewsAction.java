package com.zj.business.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.core.control.struts.BaseAction;

@Component("newsAction")
@Scope("prototype")
public class NewsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411811885607446963L;
//	private News news;
//	@Resource
//	private INewsSerivce newsService;
//	private int rp; // page size
//	private int page; // page num
//	private String ids; // users id which need to be deleted
//	private Long id; // news id which need to be modify
//	
//	private String query;
//	private String qtype;
//	
//	//multiple file upload
//	private File[] imageFiles;
//	private String[] imageFilesContentType;
//	private String[] imageFilesFileName;
//	
//	public String save(){
//		boolean hasAttatchment = false;
//		String attachmentDirPath = "";
//		try{
//			if(imageFiles != null){
//				hasAttatchment = true;
//				attachmentDirPath = "upload/news/"+System.currentTimeMillis();
//				news.setImageDir(attachmentDirPath);
//			}
//			news.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
//			news.setCreateTime(new Date());
//			newsService.insert(news);
//			if(hasAttatchment){
//				String absoluteUrl = getBasePath()+attachmentDirPath;
//				File dir = new File(absoluteUrl);
//				if(!dir.exists()){
//					dir.mkdirs();
//				}
//				for(int i=0;i<imageFiles.length;i++){
//					String attachFileName = (i+1)+getExtention(imageFilesFileName[i]);
//					String filePath = absoluteUrl+"/"+attachFileName;
//					File destFile = new File(filePath);
//					copyByChannel(imageFiles[i], destFile);
//				}
//			}
//			getValueStack().set("msg","Add News ["+news.getTitleEn()+"] Successfully!");
//			return "save_success";
//		}catch(ServiceException se){
//			se.printStackTrace();
//			Log.info(EditorialAction.class, se.getMessage());
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!" );
//			return "save_failure";
//		}catch(UploadFileException ue){
//			ue.printStackTrace();
//			Log.info(EditorialAction.class, ue.getMessage());
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!" );
//			return "save_failure";
//		}catch(Exception e){
//			e.printStackTrace();
//			Log.debug(EditorialAction.class,e.getMessage());
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!" );
//			return "save_failure";
//		}
//	}
//	
//	/**
//	 * 
//	 *
//	 * Describle(描述)：display all news list in index.jsp of News module
//	 *
//	 * 方法名称：showAll
//	 *
//	 * 所在类名：NewsAction
//	 *
//	 * 返回类型：void
//	 *
//	 * Operate Time:2013-7-16 下午11:33:44
//	 *
//	 *
//	 */
//	public void showAll(){
//		try {
//			PageInfo<News> pageinfo = newsService.loadNewssForPage(rp, page);
//			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
//			sendJSONdata(json);
//		} catch (ServiceException e){
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 
//	 *
//	 * Describle(描述)：batch delete news 
//	 *
//	 * 方法名称：delete
//	 *
//	 * 所在类名：NewsAction
//	 *
//	 * 返回类型：void
//	 *
//	 * Operate Time:2013-7-16 下午11:25:47
//	 *
//	 *
//	 */
//	public void delete(){
//		try {
//			Long[] keys = StringUtil.convertArray(ids);
//			newsService.bulkDelete(News.class, keys);
//			String msg = "Delete[" + keys.length + "] News Successfully !";
//			String json = JSONUtil.stringToJson(msg);
//			sendJSONdata(json);
//		} catch (ServiceException e) {
//			String msg = "Delete News Failure !";
//			String json = JSONUtil.stringToJson(msg);
//			sendJSONdata(json);
//			Log.debug(NewsAction.class, e.getMessage());
//		}
//	}
//
//	/**
//	 * 
//	 *
//	 * Describle(描述)：search news by title
//	 *
//	 * 方法名称：doSearch
//	 *
//	 * 所在类名：NewsAction
//	 *
//	 * 返回类型：void
//	 *
//	 * Operate Time:2013-7-16 下午11:26:11
//	 *
//	 *
//	 */
//	public void doSearch(){
//		try {
//			PageInfo<News> pageinfo = newsService.searchList(rp, page, qtype, query);
//			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
//			sendJSONdata(json);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 
//	 *
//	 * Describle(描述)：transfer db news value to modify page
//	 *
//	 * 方法名称：modifyForward
//	 *
//	 * 所在类名：NewsAction
//	 *
//	 * 返回类型：String
//	 *
//	 * Operate Time:2013-7-16 下午11:32:23
//	 *
//	 *
//	 * @return
//	 */
//	public String modifyForward(){
//		try {
//			News dbNews = newsService.get(News.class, id);
//			getValueStack().setValue("news", dbNews);
//			return "modify_forward_successful";
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			Log.debug(NewsAction.class, "cannot find this news in db");
//			getValueStack().setValue("msg", "there occurred some error! please attempt again!");
//			return "modify_forward_failure";
//		}
//	}
//	
//	/**
//	 * 
//	 *
//	 * Describle(描述)：update news
//	 *
//	 * 方法名称：update
//	 *
//	 * 所在类名：NewsAction
//	 *
//	 * 返回类型：String
//	 *
//	 * Operate Time:2013-7-16 下午11:33:08
//	 *
//	 *
//	 * @return
//	 */
//	public String update(){
//		UpdateMode mode = UpdateMode.MINI;
//		boolean hasAttachment = false;
//		String attachmentDirPath = news.getImageDir();
//		try{
//			if(imageFiles != null){
//				hasAttachment = true;
//				mode = UpdateMode.NORMAL;
//				if(attachmentDirPath == null || "".equals(attachmentDirPath)){
//					attachmentDirPath = "upload/news/"+System.currentTimeMillis();
//					news.setImageDir(attachmentDirPath);
//				}
//			}
//			news.setModifier(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
//			news.setModifiedTime(new Date());
//			newsService.merge(news, news.getNewsId(), mode);
//			if(hasAttachment){
//				String absoluteUrl = getBasePath()+attachmentDirPath;
//				File dir = new File(absoluteUrl);
//				if(!dir.exists()){
//					dir.mkdirs();
//				}
//				File[] listFile = dir.listFiles();
//				if(listFile != null){
//					for(File file:listFile){
//						file.delete();
//					}
//				}
//				//add new files
//				for(int i=0;i<imageFiles.length;i++){
//					String imageFileName = (i+1)+getExtention(imageFilesFileName[i]);
//					String saveUrl = absoluteUrl+"/"+imageFileName;
//					File destFile = new File(saveUrl);
//					copyByChannel(imageFiles[i],destFile);
//				}
//			}
//			getValueStack().set("msg", "update news ["+news.getTitleEn()+"] successfully");
//			return "modify";
//		}catch(ServiceException se){
//			se.printStackTrace();
//			Log.info(NewsAction.class, se.getMessage());
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!" );
//			return "modify";
//		}catch(UploadFileException ue){
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!" );
//			ue.printStackTrace();
//			Log.debug(NewsAction.class, ue.getMessage());
//			return "modify";
//		}catch(Exception e){
//			e.printStackTrace();
//			Log.debug(NewsAction.class, e.getMessage());
//			getValueStack().set("msg", "Add News ["+news.getTitleEn()+"] failure!");
//			return "modify";
//		}
//	}
//	
//	public String loadAll(){
//		try {
//			String language = "en_US";
//			Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
//			if (sessionLocale != null && sessionLocale instanceof Locale) {
//	            Locale locale = (Locale) sessionLocale;
//	            language = locale.getLanguage()+"_"+locale.getCountry();
//	        }
//			List<News> allNews = newsService.loadAllNews();
//			List<NewsVO> newslist = new ArrayList<NewsVO>();
//			String basePath = getBasePath();
//			for(News news:allNews){
//				NewsVO vo = new NewsVO(news, basePath);
//				vo.process(language);
//				newslist.add(vo);
//			}
//			getValueStack().set("newslist", newslist);
//			return "load_news_success";
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			getValueStack().set("msg", "load news error!");
//			return "load_news_failure";
//		}
//	}
//	
//	public String showDetails(){
//		try {
//			String language = "en_US";
//			Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
//			if (sessionLocale != null && sessionLocale instanceof Locale) {
//	            Locale locale = (Locale) sessionLocale;
//	            language = locale.getLanguage()+"_"+locale.getCountry();
//	        }
//			News dbnews = newsService.get(News.class, news.getNewsId());
//			NewsVO vo = new NewsVO(dbnews,getBasePath());
//			vo.process(language);
//			getValueStack().set("specnews",vo);
//			return "open_news_success";
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			return "open_news_failure";
//		}
//	}
//	
//	public String showPreItem(){
//		String language = "en_US";
//		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
//		if (sessionLocale != null && sessionLocale instanceof Locale) {
//            Locale locale = (Locale) sessionLocale;
//            language = locale.getLanguage()+"_"+locale.getCountry();
//        }
//		try {
//			News preNews = newsService.getPreNews(news.getNewsId());
//			NewsVO vo = new NewsVO(preNews,getBasePath());
//			vo.process(language);
//			getValueStack().set("specnews",vo);
//			return "open_news_success";
//		} catch (ServiceException e) {
//			Log.debug(NewsAction.class, e.getMessage());
//			getValueStack().set("msg",e.getMessage());
//			try {
//				news = newsService.get(News.class, news.getNewsId());
//				NewsVO vo = new NewsVO(news,getBasePath());
//				vo.process(language);
//				getValueStack().set("specnews",vo);
//			} catch (ServiceException e1) {
//				e1.printStackTrace();
//			}
//			return "open_news_failure";
//		}
//	}
//	
//	public String showNextItem(){
//		String language = "en_US";
//		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
//		if (sessionLocale != null && sessionLocale instanceof Locale) {
//            Locale locale = (Locale) sessionLocale;
//            language = locale.getLanguage()+"_"+locale.getCountry();
//        }
//		try {
//			News nextNews = newsService.getNextNews(news.getNewsId());
//			NewsVO vo = new NewsVO(nextNews,getBasePath());
//			vo.process(language);
//			getValueStack().set("specnews",vo);
//			return "open_news_success";
//		} catch (ServiceException e) {
//			Log.debug(NewsAction.class, e.getMessage());
//			getValueStack().set("msg",e.getMessage());
//			try {
//				news = newsService.get(News.class, news.getNewsId());
//				NewsVO vo = new NewsVO(news,getBasePath());
//				vo.process(language);
//				getValueStack().set("specnews",vo);
//			} catch (ServiceException e1) {
//				e1.printStackTrace();
//			}
//			return "open_news_failure";
//		}
//	}
//	
//	public News getNews() {
//		return news;
//	}
//
//	public void setNews(News news) {
//		this.news = news;
//	}
//
//	public int getRp() {
//		return rp;
//	}
//
//	public void setRp(int rp) {
//		this.rp = rp;
//	}
//
//	public int getPage() {
//		return page;
//	}
//
//	public void setPage(int page) {
//		this.page = page;
//	}
//
//	public String getIds() {
//		return ids;
//	}
//
//	public void setIds(String ids) {
//		this.ids = ids;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getQuery() {
//		return query;
//	}
//
//	public void setQuery(String query) {
//		this.query = query;
//	}
//
//	public String getQtype() {
//		return qtype;
//	}
//
//	public void setQtype(String qtype) {
//		this.qtype = qtype;
//	}
//
//	public File[] getImageFiles() {
//		return imageFiles;
//	}
//
//	public void setImageFiles(File[] imageFiles) {
//		this.imageFiles = imageFiles;
//	}
//
//	public String[] getImageFilesContentType() {
//		return imageFilesContentType;
//	}
//
//	public void setImageFilesContentType(String[] imageFilesContentType) {
//		this.imageFilesContentType = imageFilesContentType;
//	}
//
//	public String[] getImageFilesFileName() {
//		return imageFilesFileName;
//	}
//
//	public void setImageFilesFileName(String[] imageFilesFileName) {
//		this.imageFilesFileName = imageFilesFileName;
//	}
}
