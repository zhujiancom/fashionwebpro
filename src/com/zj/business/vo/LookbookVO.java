package com.zj.business.vo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zj.bigdefine.CommonConstant;
import com.zj.business.observer.LanguageType;
import com.zj.business.po.Lookbook;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.FileUtil;
import com.zj.common.utils.StringUtil;

public class LookbookVO  extends AbstractVO{
	private static final Log log = LogFactory.getLog(EditorialVO.class);
	
	private long id;
	private Lookbook lookbook;
	private final String root;
	private List<String> images;
	private List<String> thumbnailUrls;
	private String name;
	
	public LookbookVO(String path){
		this.root = path;
	}
	
	public LookbookVO(Lookbook lookbook, String path){
		super();
		this.lookbook = lookbook;
		this.root = path;
		init();
	}
	
	public void init(){
		setId(lookbook.getLookbookid());
		images = new LinkedList<String>();
		thumbnailUrls = new LinkedList<String>();
		if(!StringUtil.isEmpty(lookbook.getImgs())){
			String absoluteDirPath = root + lookbook.getImgs();
			try{
				File imageDir = FileUtil.generateDirectory(absoluteDirPath);
				// load images
				Collection<File> UnThumbnails = FileUtil.listFiles(imageDir,
						FileFilterUtils.asFileFilter(new FilenameFilter() {

							@Override
							public boolean accept(File dir, String name) {
								if (name.indexOf(CommonConstant.ThumbnailSuffix) == -1) {
									return true;
								}
								return false;
							}
						}), null);
				for (File unThumbnail : UnThumbnails) {
					String thumbnailPath = unThumbnail.getAbsolutePath();
					String surfixPath = thumbnailPath.substring(root.length());
					surfixPath = StringUtil.replaceChars(surfixPath, '\\', '/');
					images.add(surfixPath);
				}

				// load thumbnails
				Collection<File> thumbnails = FileUtil.listFiles(imageDir,
						FileFilterUtils.asFileFilter(new FilenameFilter() {

							@Override
							public boolean accept(File dir, String name) {
								if (name.matches(".+("+ CommonConstant.ThumbnailSuffix+ ").+")) {
									return true;
								}
								return false;
							}
						}), null);
				for(File thumbnail:thumbnails){
					String thumbnailPath = thumbnail.getAbsolutePath();
					String surfixPath = thumbnailPath.substring(root.length());
					surfixPath = StringUtil.replaceChars(surfixPath, '\\', '/');
					thumbnailUrls.add(surfixPath);
				}
			}catch (ServiceException e) {
				log.debug("create directory in LookbookVO class", e);
			}
		}
		setName(lookbook.getLookbookEname());
	}

	@Override
	protected void setEnglishValue() {
		setName(lookbook.getLookbookEname());
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setName(convertTCSC(lookbook.getLookbookCname(),language));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lookbook getLookbook() {
		return lookbook;
	}

	public void setLookbook(Lookbook lookbook) {
		this.lookbook = lookbook;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getThumbnailUrls() {
		return thumbnailUrls;
	}

	public void setThumbnailUrls(List<String> thumbnailUrls) {
		this.thumbnailUrls = thumbnailUrls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
