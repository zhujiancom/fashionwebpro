package com.zj.core.control.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ckfinder.connector.utils.ImageUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.zj.bigdefine.CommonConstant;
import com.zj.business.observer.LanguageType;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.core.service.ICommonService;

@Component("baseAction")
@Scope("prototype")
public class BaseAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware,ParameterAware{
	private static final Logger log = Logger.getLogger(BaseAction.class);
	/*定义缓冲区大小16M*/
	private static final int BUFFER_SIZE = 16*1024;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1280139730901662991L;

	protected Map<String,Object> session ;
	protected Map<String,Object> request;
	protected Map<String,Object> application;
	protected Map<String,String[]> parameters;
	
	@Resource
	private ICommonService commonService;
	
	public BaseAction() {
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void sendJSONdata(Object obj){
		if(obj==null){
    		return ;
    	}
		HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null  ;
		try {
			out = response.getWriter();
            out.println(obj);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
        	if(out!=null){
        		out.close();
        	}
        }
	}
	
	public ValueStack getValueStack(){
		return ActionContext.getContext().getValueStack();
	}
	
	public static void copy(File src,File dest) throws UploadFileException{
		InputStream in = null;
		OutputStream out = null;
		try{
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dest), BUFFER_SIZE);
				int temp=0;
				while((temp=in.read())!=-1){
					out.write(temp);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		finally{
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void copyByChannel(File src,File dest)throws UploadFileException{
		FileInputStream fin = null;
		FileOutputStream fout = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {
			fin = new FileInputStream(src);
			fout = new FileOutputStream(dest);
			fcin = fin.getChannel();
			fcout = fout.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
			while(true){
				buffer.clear();
				int r = fcin.read(buffer); //判断拷贝完成
				if(r ==-1){
					break;
				}
				buffer.flip();
				fcout.write(buffer);
			}
		} catch (FileNotFoundException e) {
			throw new UploadFileException(e.getMessage());
		} catch (IOException e) {
			throw new UploadFileException(e.getMessage());
		}finally{
				try {
					if(fin != null){
						fin.close();
					}
					if(fout != null){
						fout.close();
					}
					fcin.close();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/*得到文件的扩展名*/
	public static String getExtention(String fileName){
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：accquire interal code
	 *
	 * 方法名称：getCode
	 *
	 * 所在类名：BaseAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 下午10:37:36
	 *
	 *
	 */
	public void getCode(){
		try {
			String code = commonService.generateInteralCode();
			String json = JSONUtil.stringToJson(code);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBasePath(){
		String basePath = ServletActionContext.getServletContext().getRealPath("/");
//		int endIndex_win = basePath.lastIndexOf("\\");
//		int endIndex_linux = basePath.lastIndexOf("/");
//		int endIndex = (endIndex_win != -1)? endIndex_win:endIndex_linux;
//		String serverPath = basePath.substring(0,endIndex);
		return basePath;
	}
	
	public String getWebRootPath(){
		String scheme = ServletActionContext.getRequest().getScheme(); // http
		String contextPath = ServletActionContext.getRequest().getContextPath();  // project name
		String serverName = ServletActionContext.getRequest().getServerName(); //localhost
		int port = ServletActionContext.getRequest().getServerPort(); // 8080
		String path = scheme+"://"+serverName+":"+port+contextPath+"/";
		return path;
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：prepare delete old file before upload new file
	 *
	 * 方法名称：preDeleteFile
	 *
	 * 所在类名：BaseAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-9-29 上午09:55:17
	 *
	 *
	 * @param fileName
	 * @throws UploadFileException
	 */
	protected void preDeleteFile(String fileName) throws UploadFileException{
		fileName = (fileName == null || getBasePath().equals(fileName))? "":fileName;
		File targetFile = new File(fileName);
		if(targetFile.exists()){
			log.debug("prepare delete old file <"+fileName+">.");
			try {
				FileUtils.forceDelete(targetFile);
			} catch (IOException e) {
				throw new UploadFileException("delete old file <"+fileName+"> occured error!",e);
			}
			log.debug("delete file <"+fileName+"> success!");
			StringBuffer sb = new StringBuffer(fileName);
			String thumbnailUrl = sb.insert(fileName.lastIndexOf("."),CommonConstant.ThumbnailSuffix).toString();
			File thumbnail = new File(thumbnailUrl);
			if(thumbnail.exists()){
				log.debug("prepare delete old thumbnail file <"+thumbnailUrl+">.");
				try {
					FileUtils.forceDelete(thumbnail);
				} catch (IOException e) {
					throw new UploadFileException("delete old thumbnail file <"+thumbnailUrl+"> occured error!",e);
				}
				log.debug("delete thumbnail file <"+thumbnailUrl+"> success!");
			}else{
				log.debug("no thumbnail need to be deleted!");
			}
		}else{
			log.debug("no file need to be deleted!");
		}
	}
	
	protected void preDeleteDirectory(File targetDir) throws UploadFileException{
		if(targetDir.exists()){
			log.debug("prepare cleaning directory <"+targetDir.getAbsolutePath()+">.");
			try {
				FileUtils.cleanDirectory(targetDir);
			} catch (IOException e) {
				throw new UploadFileException("clean old directory <"+targetDir.getAbsolutePath()+"> occured error!",e);
			}
			log.debug("clean directory <"+targetDir.getAbsolutePath()+"> success!");
		}else{
			log.debug("no directory need to clean!");
			if(!targetDir.mkdirs()){
				throw new UploadFileException("crate target directory error!");
			}
		}
	}
	
	protected LanguageType getLanguageType(){
		String lang = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
            Locale locale = (Locale) sessionLocale;
            lang = locale.getLanguage()+"_"+locale.getCountry();
        }else{
        	HttpServletRequest req = ServletActionContext.getRequest();
    		Locale loc = req.getLocale();
    		lang = loc.getLanguage()+"_"+loc.getCountry();
        }
		LanguageType type = LanguageType.toLanguageType(lang.toUpperCase());
		return type;
	}
	
	protected void uploadSingleImage(File tmpFile,String originalImage,String thumbnail) throws UploadFileException{
		File destFile = new File(originalImage);
		if (!destFile.getParentFile().exists()) {
			if(!destFile.getParentFile().mkdirs()){
				throw new UploadFileException("create parent floder error!");
			}
		}
		File destThumbnail = new File(thumbnail);
		copyByChannel(tmpFile, destFile);
		try {
			ImageUtils.createResizedImage(tmpFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
		} catch (NumberFormatException e) {
			log.debug("upload single image size numberical exception", e);
			throw new UploadFileException("upload single image size numberical exception", e);
		} catch (IOException e) {
			log.debug("upload single image io exception", e);
			throw new UploadFileException("upload single image io exception", e);
		}
	}
	
	protected void transferFilesToDirectory(File[] imageFiles,String[] fileNames,String absolutePath) throws UploadFileException{
		for(int i=0;i<imageFiles.length;i++){
			String imageName = UUID.randomUUID().toString();
			String fileType = getExtention(fileNames[i]);
			String imageFilePath = absolutePath+imageName+fileType;
			String thumbnailPath = absolutePath+imageName+CommonConstant.ThumbnailSuffix+fileType;
			File destFile = new File(imageFilePath);
			File destThumbnail = new File(thumbnailPath);
			copyByChannel(imageFiles[i],destFile);
			try {
				ImageUtils.createResizedImage(imageFiles[i], destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			} catch (NumberFormatException e) {
				throw new UploadFileException(e);
			} catch (IOException e) {
				throw new UploadFileException(e);
			}
		}
	}
}
