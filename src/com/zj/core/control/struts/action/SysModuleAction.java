package com.zj.core.control.struts.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;

import com.zj.common.cache.EHCacheService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysRole;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysModuleService;
import com.zj.core.service.ISysUserService;

@Component("sysmoduleAction")
@Scope("prototype")
public class SysModuleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083013078849064935L;
	private Long userId;
	private JSONArray jsonArray;
	private Long parentId;
	@Resource
	private EHCacheService ehCacheService;
	@Resource
	private ISysModuleService moduleService;
	@Resource
	private ISysUserService userService;
	
	public String addModule(){
		return "";
	}
	
	public String moduleList(){
		try {
			String key = userId+"_"+parentId;
			List<ZTreeNode> modules = ehCacheService.getModuleCache().get(key);
			if( modules != null){
				jsonArray = JSONUtil.sendArray(modules, null);
			}else{
				SysUser user = userService.get(SysUser.class, userId);
				Set<SysRole> roles = user.getSysRoles();
				modules = moduleService.loadModulesByRoles(roles, parentId);
				jsonArray = JSONUtil.sendArray(modules, null);
				ehCacheService.addOrUpdateModuleCache(key, modules);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
}
