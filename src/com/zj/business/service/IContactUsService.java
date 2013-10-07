package com.zj.business.service;

import com.zj.business.po.ContactUs;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IContactUsService extends ICommonService {

	public PageInfo<ContactUs> loadContactUsForPage(int pageSize,int pageNum) throws ServiceException;
}
