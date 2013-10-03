package com.zj.core.service;

import com.zj.common.exception.ServiceException;
import com.zj.core.po.SysRole;

public interface ISysRoleService extends ICommonService {
	public SysRole getRootRole() throws ServiceException;
}
