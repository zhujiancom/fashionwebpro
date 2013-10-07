package com.zj.business.service;

import com.zj.business.po.Account;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IAccountService extends ICommonService {
	public Account login(Account account) throws ServiceException;
	public PageInfo<Account> loadAccountList(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Account> searchAccountList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public Account getAccount(String accountname) throws ServiceException;
}
