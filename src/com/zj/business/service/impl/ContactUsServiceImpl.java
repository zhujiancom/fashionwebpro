package com.zj.business.service.impl;

import org.springframework.stereotype.Component;

import com.zj.business.po.ContactUs;
import com.zj.business.service.IContactUsService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("contactService")
public class ContactUsServiceImpl extends CommonServiceImpl
		implements
			IContactUsService {

	@Override
	public PageInfo<ContactUs> loadContactUsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from ContactUs contactus";
		PageInfo<ContactUs> contactus = dao.queryHQLForPage(hql, pageSize,
					pageNum);
			return contactus;
	}


}
