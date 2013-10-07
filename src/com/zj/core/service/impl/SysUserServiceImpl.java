package com.zj.core.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysUserService;

@Component("userService")
@Scope("prototype")
public class SysUserServiceImpl extends CommonServiceImpl implements
		ISysUserService {

	@Override
	public SysUser login(SysUser user) throws ServiceException {
		SysUser user_temp_po = null;
		List<SysUser> list = dao.queryHQL("from SysUser user where user.loginId='"+user.getLoginId()+"' and user.pswd='"+user.getPswd()+"'");
		if(list != null && !list.isEmpty()){
			user_temp_po = list.get(0);
			if(GlobalParam.ENABLE != user_temp_po.getIsEnable()){ // the user has not be enabled
				return user_temp_po;
			}else{
				int loginTimes = user_temp_po.getLoginCount();
				user_temp_po.setLoginCount(loginTimes+1);
				user_temp_po.setStatus(GlobalParam.ONLINE_STATUS);
				user_temp_po.setLoginTime(new Date(System.currentTimeMillis()));
				dao.update(user_temp_po);
				return user_temp_po;
			}
		}else{
			throw new ServiceException("The user is not Exist!");
		}
	}

	@Override
	public PageInfo<SysUser> loadUserList(int pageSize, int pageNum)
			throws ServiceException {
		return dao.queryHQLForPage("from SysUser user where user.loginId != 'admin'", pageSize, pageNum);
	}

	@Override
	public PageInfo<SysUser> searchUserList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from SysUser user where user."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public void logout(SysUser user) throws ServiceException {
		user.setStatus(GlobalParam.OFFLINE_STATUS);
		merge(user, user.getUserId(), UpdateMode.MINI);
	}

}
