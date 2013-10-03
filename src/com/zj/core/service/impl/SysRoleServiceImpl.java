package com.zj.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.common.exception.ServiceException;
import com.zj.core.po.SysRole;
import com.zj.core.service.ISysRoleService;

@Component("roleService")
public class SysRoleServiceImpl extends CommonServiceImpl implements
		ISysRoleService {

	@Override
	public SysRole getRootRole() throws ServiceException {
		List<SysRole> roots = dao.queryHQL("from SysRole role where role.roleEname='admin'");
		if(roots == null || roots.isEmpty()){
			throw new ServiceException("there is no super role object!");
		}
		return roots.get(0);
	}
	
}
