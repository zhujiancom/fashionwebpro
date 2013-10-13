package com.zj.business.vo;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

import com.zj.common.exception.ServiceException;

public class VOFactory {
	private static final Logger log = Logger.getLogger(VOFactory.class);
	@SuppressWarnings("rawtypes")
	public static <T> T getObserverVO(Class<T> clazz, Object... params) throws ServiceException{
		try {
			if (params != null && params.length > 0) {
				Class[] paramTypes = new Class[params.length];
				for (int i = 0; i < params.length; i++) {
					paramTypes[i] = params[i].getClass();
				}
				Constructor<T> c = clazz.getConstructor(paramTypes);
				return c.newInstance(params);
			} else {
				Constructor<T> c = clazz.getConstructor();
				return c.newInstance();
			}
		} catch (Exception e) {
			log.debug("create VO error!", e);
			throw new ServiceException("create VO error!",e);
		} 
	}
}
