package com.zj.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Account;
import com.zj.business.service.IAccountService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("accountService")
public class AccountServiceImpl extends CommonServiceImpl implements
		IAccountService {

	@Override
	public Account login(Account account) throws ServiceException {
		Account account_temp_po = null;
		List<Account> list = dao.queryHQL("from Account account where account.accountname='"+account.getAccountname()+"' and account.pswd='"+account.getPswd()+"'");
		if(list != null && !list.isEmpty()){
			account_temp_po = list.get(0);
			if(GlobalParam.ENABLE != account_temp_po.getIsEnable()){ // the user has not be enabled
				return account_temp_po;
			}else{
				int loginTimes = account_temp_po.getLoginCount();
				account_temp_po.setLoginCount(loginTimes+1);
				account_temp_po.setIsEnable(GlobalParam.ONLINE_STATUS);
				account_temp_po.setLastLoginTime(new Date(System.currentTimeMillis()));
				dao.update(account_temp_po);
				return account_temp_po;
			}
		}else{
			return null;
		}
	}

	@Override
	public PageInfo<Account> loadAccountList(int pageSize, int pageNum)
			throws ServiceException {
		return dao.queryHQLForPage("from  Account account", pageSize, pageNum);
	}

	@Override
	public PageInfo<Account> searchAccountList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Account account where account."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);

	}

	@Override
	public Account getAccount(String accountname) throws ServiceException {
		List<Account> accounts =dao.queryHQL("from Account a where a.accountname='"+accountname+"'");
		if(accounts == null || accounts.isEmpty()){
			throw new ServiceException("the account is not exist");
		}
		return accounts.get(0);
	}

}
