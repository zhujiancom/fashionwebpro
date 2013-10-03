package com.zj.core.converter;

import java.lang.reflect.Field;

import com.zj.common.exception.ConvertException;

public interface IConverter {
	public void convert(Object entity,Object domain,Boolean entityTodomain) throws ConvertException;
	
	public void convertEntityToDomain(Object entity,Object domain,Field entityField,Field domainField) throws ConvertException;

	public void convertDomainToEntity(Object domain,Object entity,Field entityField,Field domainField) throws ConvertException;
}
