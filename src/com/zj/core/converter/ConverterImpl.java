package com.zj.core.converter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.hibernate.mapping.Collection;

import com.zj.common.annotation.CategoryGroup;
import com.zj.common.exception.ConvertException;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.po.SysCategory;

public class ConverterImpl implements IConverter {
	private ICommonDAO dao;
	
	
	public ICommonDAO getDao() {
		return dao;
	}

	public void setDao(ICommonDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void convertEntityToDomain(Object entity,Object domain,Field entityField,
			Field domainField) throws ConvertException {
			try {
				entityField.setAccessible(true);
				Object entityFieldValue = entityField.get(entity);
				domainField.setAccessible(true);
				CategoryGroup categoryGroup = entityField.getAnnotation(CategoryGroup.class);
				if(categoryGroup != null){
					String categoryKey = categoryGroup.referneceKey();
					List<SysCategory> categories = dao.queryHQL("from SysCategory category where category.parent.categoryGroupCd='"+categoryKey+"' and category.categoryCd='"+entityFieldValue+"'");
					if(categories != null && !categories.isEmpty()){
						SysCategory category = categories.get(0);
						domainField.set(domain, category.getCname());
					}
				}else{
					domainField.set(domain, entityFieldValue);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void convertDomainToEntity(Object domain,
			Object entity,Field entityField,Field domainField) throws ConvertException {
		try {
			domainField.setAccessible(true);
			Object domainFieldValue = domainField.get(domain);
			entityField.setAccessible(true);
			CategoryGroup categoryGroup = entityField.getAnnotation(CategoryGroup.class);
			if(categoryGroup != null){
				String categoryKey = categoryGroup.referneceKey();
				List<SysCategory> categories = dao.queryHQL("from SysCategory category where category.parent.categoryGroupCd='"+categoryKey+"' and category.cname='"+domainFieldValue+"'");
				if(categories != null && !categories.isEmpty()){
					SysCategory category = categories.get(0);
					entityField.set(entity, category.getCategoryCd());
				}
			}else{
				entityField.set(entity, domainFieldValue);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void convert(Object entity, Object domain, Boolean entityTodomain)
			throws ConvertException {
//		MappingClass mappingClazz = entity.getClass().getAnnotation(MappingClass.class);
//		Class entityClazz = entity.getClass();
//		Class domainClazz = domain.getClass();
//		if(mappingClazz != null && mappingClazz.mappingClass().equals(domain.getClass().getCanonicalName())){
//			Field[] entityFields = entityClazz.getDeclaredFields();
//			for(Field entityField:entityFields){
//				MappingField mappingField = entityField.getAnnotation(MappingField.class);
//				if(mappingField != null){
//					String domainFieldName = mappingField.mappingField();
//					try {
//						Field domainField = domainClazz.getDeclaredField(domainFieldName);
//						if(entityTodomain){
//							convertEntityToDomain(entity,domain,entityField,domainField);
//						}else{
//							convertDomainToEntity(domain,entity,entityField,domainField);
//						}
//					} catch (SecurityException e) {
//						throw new ConvertException("the filed is not exist!");
//					} catch (NoSuchFieldException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
	}
}
