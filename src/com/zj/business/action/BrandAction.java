package com.zj.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.po.Style;
import com.zj.business.service.IBrandService;
import com.zj.business.vo.BrandVO;
import com.zj.common.exception.ServiceException;
import com.zj.common.exception.UploadFileException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;
import com.zj.common.ztreenode.NodeType;
import com.zj.common.ztreenode.TreeNode;
import com.zj.common.ztreenode.TreeNodeImpl;
import com.zj.common.ztreenode.TreeNodeStrategyFactory;
import com.zj.common.ztreenode.ZTreeNode;
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
		boolean isAddImg = false;
		try {
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				isAddImg = true;
				String imageFileName = new Date().getTime()
						+ getExtention(imageFileFileName);
				imgUrl = "upload/headImg/brand/" + imageFileName;
				brand.setBrandimg(imgUrl);
			}
			brand.setCreater(((SysUser) session
					.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			brand.setCreateTime(new Date());
			brandService.save(brand, designer);
			if (isAddImg) {
				String absoluteUrl = getBasePath() + imgUrl;
				File destFile = new File(absoluteUrl);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				copyByChannel(imageFile, destFile);
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
					"create brand " + brand.getBrandEname() + " Failure! ");
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
									+ " Failed, because of seesion time out, please relogin! ");
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
			getValueStack().setValue("brand", dbbrand);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			log.debug("The brand is not exist!",e);
			return "modify_forward_failure";
		}
	}

	public String update() {
		String imgurl = "";
		String oldImgurl = getBasePath() + brand.getBrandimg();
		boolean isUpdateImg = false;
		try {
			if (imageFileFileName != null && !"".equals(imageFileFileName)) {
				isUpdateImg = true;
				String imageFileName = new Date().getTime()
						+ getExtention(imageFileFileName);
				imgurl = "upload/headImg/brand/" + imageFileName;
				brand.setBrandimg(imgurl);
			}
			
			brand.setModifiedTime(new Date());
			brand.setModifier(((SysUser) session
					.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			brandService.update(brand, designer);
			if (isUpdateImg) {
				preDeleteFile(oldImgurl);
				String absolutePath = getBasePath() + imgurl;
				File destFile = new File(absolutePath);
				copyByChannel(imageFile, destFile);
			}
			getValueStack().set(
					"msg",
					"update Brand [" + brand.getBrandEname()
							+ "] successfully!");
		} catch (ServiceException se) {
			getValueStack().set("msg",
					"update Brand [" + brand.getBrandEname() + "] failure!");
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
					"update Brand [" + brand.getBrandEname() + "] info failed, because of seesion time out ,please relogin!");
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

	public String showBrandDetail() {
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			language = locale.getLanguage() + "_" + locale.getCountry();
		}
		if (brand.getBrandid() != 0) {
			long brandId = brand.getBrandid();
			try {
				Brand b = brandService.get(Brand.class, brandId);
				BrandVO bvo = new BrandVO(b);
				bvo.process(language);
				getValueStack().set("brandVO", bvo);
				return "load_brand_success";
			} catch (ServiceException e) {
				e.printStackTrace();
				return "load_brand_failure";
			}
		}
		return "load_brand_failure";
	}

	// below methods all requests from frontend
	public String loadAll() {
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			language = locale.getLanguage() + "_" + locale.getCountry();
		}
		try {
			List<Brand> brands = brandService.getAll(Brand.class);
			List<BrandVO> brandvos = new ArrayList<BrandVO>();
			for (Brand b : brands) {
				BrandVO bvo = new BrandVO(b);
				bvo.process(language);
				brandvos.add(bvo);
			}
			getValueStack().set("brandlist", brandvos);
			return "load_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			return "load_failure";
		}
	}

	public String menuTree() {
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			language = locale.getLanguage() + "_" + locale.getCountry();
		}
		long brandId = brand.getBrandid();
		List<ZTreeNode> menu = new ArrayList<ZTreeNode>();
		AbstractMakeDataStrategy strategy = null;
		try {
			brand = brandService.get(Brand.class, brandId);
			if (treeid == null) {
				strategy = TreeNodeStrategyFactory.getNodeStrategy(
						NodeType.NONEFORBRAND, new Object[]{brand, language});
			}
			if (treeid != null) {
				if (nodetype.equals(NodeType.PRESSFORBRAND.toString())
						&& session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null) {
					strategy = TreeNodeStrategyFactory.getNodeStrategy(
							NodeType.PRESSFORBRAND, new Object[]{brand, treeid,
									language});
				} else if (nodetype.equals(NodeType.COLLECTIONFORBRAND
						.toString())
						&& session.get(GlobalParam.LOGIN_ACCOUNT_SESSION) != null) {
					strategy = TreeNodeStrategyFactory.getNodeStrategy(
							NodeType.COLLECTIONFORBRAND, new Object[]{brand,
									treeid, language});
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TreeNode treenode = new TreeNodeImpl(strategy);
		menu = treenode.generateNode();
		jsonArray = JSONUtil.sendArray(menu, null);
		return "generate_menutree_success";
	}

	public String showBrandInfo() {
		String language = "en_US";
		Object sessionLocale = session.get("WW_TRANS_I18N_LOCALE");
		if (sessionLocale != null && sessionLocale instanceof Locale) {
			Locale locale = (Locale) sessionLocale;
			language = locale.getLanguage() + "_" + locale.getCountry();
		}
		try {
			Long id = brand.getBrandid();
			Brand b = brandService.get(Brand.class, id);
			BrandVO bvo = new BrandVO(b);
			bvo.process(language);
			getValueStack().set("brandVO", bvo);
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
