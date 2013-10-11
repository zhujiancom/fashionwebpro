package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ckfinder.connector.utils.ImageUtils;
import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.Language;
import com.zj.business.observer.LanguageType;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.po.Style;
import com.zj.business.service.IBrandService;
import com.zj.business.treenode.BrandMenuBuilder;
import com.zj.business.treenode.IMenuBuilder;
import com.zj.business.treenode.Menu;
import com.zj.business.vo.BrandVO;
import com.zj.business.vo.DesignerVO;
import com.zj.business.vo.MenuVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;

@Component("brandAction")
@Scope("prototype")
public class BrandAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5673261561862819699L;
	private static final Logger log = Logger.getLogger(BrandAction.class);
	private Brand brand;
	private Designer designer;
	private List<Style> style;
	private Style styleobj;
	@Resource
	private IBrandService brandService;
	private String errorMsg;
	private int rp; // page size
	private int page; // page num
	private String ids; // users id which need to be deleted
	private Long id; // user id which need to be modify
	private File imageFile; // 文件名
	private String imageFileContentType; // 文件名 + ContentType (固定写法)
	private String imageFileFileName; // 文件名 + FileName (固定写法)
	private String query;
	private String qtype;

	private String term;// fuzzy search brand's full name

	// ztree param
	private String nodetype;
	private JSONArray jsonArray;
	private String treeid;

	public String save() {
		String imgUrl = "";
		String thumbnailUrl = "";
		boolean isAddImg = false;
		try {
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				isAddImg = true;
				String imageName = UUID.randomUUID().toString();
				imgUrl = "upload/headImg/brand/" + imageName+ getExtention(imageFileFileName);;
				thumbnailUrl = "upload/headImg/brand/"+imageName+CommonConstant.ThumbnailSuffix+ getExtention(imageFileFileName);
				brand.setBrandimg(imgUrl);
			}
			brand.setCreater(((SysUser) session
					.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			brand.setCreateTime(new Date());
			brandService.save(brand, designer);
			if (isAddImg) {
				String absoluteUrl = getBasePath() + imgUrl;
				String absoluteThumbnailUrl = getBasePath() +thumbnailUrl;
				File destFile = new File(absoluteUrl);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(imageFile, destFile);
				ImageUtils.createResizedImage(imageFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			}
			getValueStack()
					.set("msg",
							"create brand " + brand.getBrandEname()
									+ " Successfully! ");
			return "save_success";
		} catch (ServiceException se) {
			se.printStackTrace();
			log.debug("create brand failed!",se);
			getValueStack().set("msg",
					"create brand " + brand.getBrandEname() + " Failure! root cause is "+se.getMessage());
			return "save_failure";
		} catch (UploadFileException ue) {
			ue.printStackTrace();
			log.debug("update attachments failed!",ue);
			getValueStack()
					.set("msg",
							"create brand "
									+ brand.getBrandEname()
									+ " success, but upload attachments occured error! ");
			return "save_failure";
		} catch (Exception e) {
			e.printStackTrace();
			getValueStack()
					.set("msg",
							"create brand "
									+ brand.getBrandEname()
									+ " Failure! root cause is "+e.getMessage());
			log.debug(e);
			return "save_failure";
		}
	}

	public void showAll() {
		try {
			PageInfo<Brand> pageinfo = brandService.loadBrandsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			Long[] keys = StringUtil.convertArray(ids);
			brandService.bulkDelete(Brand.class, keys);
			String msg = "Delete[" + keys.length + "] Brands Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Brands Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug("delete brands failed!",e);
			sendJSONdata(json);
		}
	}

	public String modifyForward() {
		try {
			Brand dbbrand = brandService.get(Brand.class, id);
			BrandVO vo = new BrandVO(dbbrand);
			getValueStack().set("brandvo", vo);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The brand is not exist!",e);
			return "modify_forward_failure";
		}catch(Exception e){
			log.debug(e);
			e.printStackTrace();
			return "modify_forward_failure";
		}
	}

	public String update() {
		String imgurl = "";
		String thumbnailUrl="";
		String oldImgurl = getBasePath() + brand.getBrandimg();
		boolean isUpdateImg = false;
		try {
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				String fileType = getExtention(imageFileFileName);
				isUpdateImg = true;
				String imageName =UUID.randomUUID().toString();
				imgurl = "upload/headImg/brand/" + imageName+fileType;
				thumbnailUrl="upload/headImg/designer/"+imageName+CommonConstant.ThumbnailSuffix+fileType;
				brand.setBrandimg(imgurl);
			}
			
			brand.setModifiedTime(new Date());
			brand.setModifier(((SysUser) session
					.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			brandService.update(brand, designer);
			if (isUpdateImg) {
				preDeleteFile(oldImgurl);
				String absolutePath = getBasePath() + imgurl;
				String absoluteThumbnailUrl = getBasePath() +thumbnailUrl;
				File destFile = new File(absolutePath);
				if(!destFile.getParentFile().exists()){
					if(!destFile.getParentFile().mkdirs()){
						throw new UploadFileException("create parent floder error!");
					}
				}
				File destThumbnail = new File(absoluteThumbnailUrl);
				copyByChannel(imageFile, destFile);
				ImageUtils.createResizedImage(imageFile, destThumbnail, Integer.valueOf(System.getProperty("thumbnail.size.width")), Integer.valueOf(System.getProperty("thumbnail.size.height")),Float.valueOf( System.getProperty("thumbnail.size.quality")));
			}
			getValueStack().set(
					"msg",
					"update Brand [" + brand.getBrandEname()
							+ "] successfully!");
		} catch (ServiceException se) {
			getValueStack().set("msg",
					"update Brand [" + brand.getBrandEname() + "] failure! root cause is "+se.getMessage());
			se.printStackTrace();
			log.debug("update brand error!",se);
		} catch (UploadFileException ue) {
			getValueStack().set("msg",
					"update  Brand [" + brand.getBrandEname() + "] info success, but upload attachments occured error!");
			ue.printStackTrace();
			log.debug("upload attachments error!",ue);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			getValueStack().set("msg",
					"update Brand [" + brand.getBrandEname() + "] info failed, root cause is "+e.getMessage());
		}
		return "modify";
	}

	public void doSearch() {
		try {
			PageInfo<Brand> pageinfo = brandService.searchList(rp, page, qtype,
					query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fuzzySearch() {
		try {
			List<Brand> brands = brandService.fuzzySearchByName(term);
			List<String> names = new ArrayList<String>();
			for (Brand b : brands) {
				names.add(b.getBrandEname());
			}
			String json = JSONUtil.listToJson(names);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	// below methods all requests from frontend
	/**
	 * load all brands in brand browser page
	 */
	public String loadAll() {
		String lang = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			lang = locale.getLanguage() + "_" + locale.getCountry();
		}
		try {
			LanguageType type = LanguageType.toLanguageType(lang.toUpperCase());
			Language language = Language.getInstance();
			List<Brand> brands = brandService.getAll(Brand.class);
			List<BrandVO> brandvos = new ArrayList<BrandVO>();
			for (Brand b : brands) {
				BrandVO bvo = VOFactory.getObserverVO(BrandVO.class, b);
				brandvos.add(bvo);
				language.addObserver(bvo);
			}
			language.setLanguage(type);
			getValueStack().set("brandlist", brandvos);
			return "load_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "serviceException";
		}
	}

	/**
	 * load menu tree in frontend 
	 * @return
	 */
	public String loadMenu(){
        Language language = Language.getInstance();
		Long brandId = brand.getBrandid();
		try{
			Brand b = brandService.get(Brand.class, brandId);
			BrandVO bvo = VOFactory.getObserverVO(BrandVO.class, b);
			Designer d = b.getDesigner();
			DesignerVO dvo = VOFactory.getObserverVO(DesignerVO.class, d);
			boolean isPermission = false;
			if(session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null){
				isPermission = true;
			}
			MenuVO mvo = VOFactory.getObserverVO(MenuVO.class);
			language.addObserver(dvo);
			language.addObserver(bvo);
			language.addObserver(mvo);
			language.setLanguage(getLanguageType());
			IMenuBuilder builder = new BrandMenuBuilder(bvo,dvo,isPermission);
			List<Menu> menuTree = builder.createMenuTree();
			getValueStack().set("brandvo", bvo);
			getValueStack().set("menutree",menuTree);
		}catch (ServiceException e) {
			e.printStackTrace();
			return "serviceException";
		}
		return "loadMenu_success";
	}

	public String showBrandInfo() {
		String lang = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			lang = locale.getLanguage() + "_" + locale.getCountry();
		}
		LanguageType type = LanguageType.toLanguageType(lang.toUpperCase());
        Language language = Language.getInstance();
		try {
			Long id = brand.getBrandid();
			Brand b = brandService.get(Brand.class, id);
			BrandVO bvo = VOFactory.getObserverVO(BrandVO.class, b);
			language.addObserver(bvo);
			language.setLanguage(type);
			getValueStack().set("brandvo", bvo);
			return "load_brand_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			getValueStack().set("msg",
					"forward brand info page error,because " + e.getMessage());
			return "load_brand_failure";
		}
	}

	public String loadBrandByStyle() {
//		String language = "en_US";
//		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
//		if (sessionLocale != null && sessionLocale instanceof Locale) {
//			Locale locale = (Locale) sessionLocale;
//			language = locale.getLanguage() + "_" + locale.getCountry();
//		}
//		try {
//			styleobj = styleService.get(Style.class, styleobj.getStyleid());
//			Set<Brand> brands = styleobj.getBrands();
//			List<BrandVO> brandvos = new ArrayList<BrandVO>();
//			for (Brand b : brands) {
//				BrandVO bvo = new BrandVO(b);
//				bvo.process(language);
//				brandvos.add(bvo);
//			}
//			getValueStack().set("brandlist", brandvos);
//			return "load_success";
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			getValueStack().set("msg", e.getMessage());
//			return "load_failure";
//		}
		return "load_failure";
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Style> getStyle() {
		return style;
	}

	public void setStyle(List<Style> style) {
		this.style = style;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public String getNodetype() {
		return nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public Style getStyleobj() {
		return styleobj;
	}

	public void setStyleobj(Style styleobj) {
		this.styleobj = styleobj;
	}

}
