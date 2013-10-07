package com.zj.core.service;

import java.util.Collection;
import java.util.List;

import com.zj.common.exception.ServiceException;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.po.SysModule;
import com.zj.core.po.SysRole;

public interface ISysModuleService extends ICommonService {
	public List<SysModule> loadModulesByRoles(Collection<SysRole> roles) throws ServiceException;
	public List<ZTreeNode> loadModulesByRoles(Collection<SysRole> roles,Long parentId) throws ServiceException;
}
