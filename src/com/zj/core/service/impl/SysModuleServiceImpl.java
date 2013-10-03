package com.zj.core.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.SQLBuilder;
import com.zj.common.exception.ServiceException;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.po.SysModule;
import com.zj.core.po.SysRole;
import com.zj.core.service.ISysModuleService;

@Component("moduleService")
public class SysModuleServiceImpl extends CommonServiceImpl implements ISysModuleService{

	@Override
	public List<SysModule> loadModulesByRoles(Collection<SysRole> roles)
			throws ServiceException {
		Collection<Long> roleIds = new ArrayList<Long>();
		for(SysRole role:roles){
			roleIds.add(role.getRoleId());
		}
		String roleIdsStr = StringUtils.join(roleIds, ",");
		List<SysModule> modules = dao.queryHQL("from SysModule module where module.role.roleId in ("+roleIdsStr+")");
		return modules;
	}

	@Override
	public List<ZTreeNode> loadModulesByRoles(Collection<SysRole> roles,Long parentId) throws ServiceException {
		List<ZTreeNode> moduleTreelist = null;
		Collection<Long> roleIds = new ArrayList<Long>();
		for(SysRole role:roles){
			roleIds.add(role.getRoleId());
		}
		String roleIdsStr = StringUtils.join(roleIds, ",");
		Object[] arguments = new Object[]{parentId,roleIdsStr};
		String sql = MessageFormat.format(SQLBuilder.QUERY_MODULELIST, arguments);
		List<Map<String,Object>> list = dao.querySQL(sql);
		moduleTreelist = getZTreeNode(list,null,null);
		return moduleTreelist;
	}
}
