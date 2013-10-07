package com.zj.core.service;

import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.po.SysUser;

public interface ISysUserService extends ICommonService{
	public SysUser login(SysUser user) throws ServiceException;
	public PageInfo<SysUser> loadUserList(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<SysUser> searchUserList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public void logout(SysUser user) throws ServiceException;
}
