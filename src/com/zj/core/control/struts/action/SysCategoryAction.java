package com.zj.core.control.struts.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.CommonConstant;
import com.zj.bigdefine.GlobalParam;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysCategoryGroup;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysCategoryService;

@Component("categoryAction")
@Scope(value="prototype")
public class SysCategoryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1971025730762594002L;
	private static final Logger log = Logger.getLogger(SysCategoryAction.class);
	
	private String referenceKey;
	private long id;
	private JSONArray jsonArray;
	@Resource
	private ISysCategoryService categoryService;
	// parameters from flexgird
	private int rp; // page size
	private int page; // page num
	private String query;
	private String qtype;
	private String ids;
	// parameters from flexgird
	private String groupCd;
	
	private SysCategoryGroup group;
	private SysCategory category;
	
	public void getValue(){
		String json = "";
		try {
			List<SysCategory> categoryvalues = categoryService.loadAllItems(referenceKey);
			json = JSONUtil.listToJson(categoryvalues);
		} catch (ServiceException e) {
			log.debug("load category item error!", e);
			Map<String,Object> errormsg = new HashMap<String, Object>();
			errormsg.put(CommonConstant.ERRORMSG, e.getMessage());
			json = JSONUtil.mapToJson(errormsg);
		}finally{
			log.debug("json = "+json);
			sendJSONdata(json);
		}
		
	}

	public String treeList(){
		try {
			List<ZTreeNode> treelist = categoryService.getCategoryTreeList(id);
			jsonArray = JSONUtil.sendArray(treelist, null);
			return SUCCESS;
		} catch (ServiceException e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：display the category value below the specify categoryGroup
	 *
	 * 方法名称：showItems
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 上午12:11:53
	 *
	 *
	 */
	public void showItems(){
		try {
			PageInfo<SysCategory> categoryItems = categoryService.loadCategoryItemList(rp, page,groupCd);
			String json = JSONUtil.sendHbmObjectGrid(categoryItems);
			sendJSONdata(json);
		} catch (ServiceException e) {
			log.warn("show items error", e);
			sendJSONdata(new PageInfo<SysCategory>());
		}
	}
	
	public String createItem(){
		try {
			category.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
			category.setCreateTime(new Date());
			SysCategoryGroup group = categoryService.getGroupByCode(groupCd);
			category.setParent(group);
			categoryService.insert(category);
			getValueStack().set("msg", "create category Item Successfully! ");
		} catch (ServiceException e) {
			log.info(e);
			getValueStack().set("msg", e.getMessage());
		} catch(Exception ex){
			log.info(ex);
			getValueStack().set("msg", "session is timeout, please relogin this application!");
		}
		return "create_item";
	}
	
	public void deleteItems(){
		Long[] keys = StringUtil.convertArray(ids);
		try {
			categoryService.bulkDelete(SysCategory.class, keys , referenceKey);
			String msg = "Delete【" + keys.length + "】 Items Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		}catch(Exception e){
			String msg = "Delete Items Failure !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
			log.debug(e.getMessage());
		}
	}
	
	public String modifyItemForward(){
		try {
			SysCategory item = categoryService.loadItem(SysCategory.class, id);
			getValueStack().set("categoryItem",item);
		} catch (ServiceException e) {
			e.printStackTrace();
			log.info(e);
		}
		return "item_modify_forward";
	}
	
	public String updateItem(){
		SysCategory target = new SysCategory();
		BeanUtils.copyProperties(category, target, new String[]{"isEanble","parent"});
		try {
			categoryService.update(target);
			getValueStack().set("msg", "update category item info success!");
		} catch (ServiceException e) {
			log.info("update category item info failed!",e);
			getValueStack().set("msg", "update category item info failed!");
		}
		return "modify_item";
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：serach category value 
	 *
	 * 方法名称：doItemSearch
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-9 下午09:49:20
	 *
	 *
	 */
	public void doItemSearch(){
		try {
			PageInfo<SysCategory> pageinfo = categoryService.searchItemList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：create group category
	 *
	 * 方法名称：createGroup
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 下午10:46:01
	 *
	 *
	 */
	public String createGroup(){
//		try {
//			group.setCreater(((SysUser)session.get(GlobalParam.LOGIN_USER_SESSION)).getEname());
//			group.setCreateTime(new Date());
//			categoryService.insert(group);
//			getValueStack().set("msg", "create category group Successfully! ");
//			log.info(SysCategory.class, "add the new category group to cache");
//			CategoryMap itemsMap = new CategoryMap();
//			ApplicationInitialize.categoryMap.put(group.getCategoryGroupCd(), itemsMap);
//			categoryService.updateCategoryMap(group.getCategoryGroupCd());
//			return "create_group_successful";
//		} catch (Exception e) {
//			e.printStackTrace();
//			getValueStack().set("msg", "create category group Failure! ");
//			log.debug(SysCategory.class, e.getMessage());
//			return "create_group_failure";
//		}
		return "create_group_failure";
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：display all category groups 
	 *
	 * 方法名称：showGroups
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 上午12:12:49
	 *
	 *
	 */
	public void showGroups(){
		try {
			PageInfo<SysCategoryGroup> pageinfo = categoryService.loadCategoryGroupList(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：check the group key is uniqueness or not
	 *
	 * 方法名称：uniqueGroupKeyCheck
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 下午11:27:36
	 *
	 *
	 */
	public void uniqueGroupKeyCheck(){
		String json = "false";
		try {
			Boolean isUnique = categoryService.uniquenessCheck(SysCategoryGroup.class, "categoryGroupCd", group.getCategoryGroupCd());
			json = JSONUtil.stringToJson(String.valueOf(isUnique));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		sendJSONdata(json);
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：search category group 
	 *
	 * 方法名称：doGroupSearch
	 *
	 * 所在类名：SysCategoryAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-8 下午08:48:07
	 *
	 *
	 */
	public void doGroupSearch(){
		try {
			PageInfo<SysCategoryGroup> pageinfo = categoryService.searchGroupList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(String referenceKey) {
		this.referenceKey = referenceKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SysCategoryGroup getGroup() {
		return group;
	}

	public void setGroup(SysCategoryGroup group) {
		this.group = group;
	}

	public SysCategory getCategory() {
		return category;
	}

	public void setCategory(SysCategory category) {
		this.category = category;
	}

	public String getGroupCd() {
		return groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

}
