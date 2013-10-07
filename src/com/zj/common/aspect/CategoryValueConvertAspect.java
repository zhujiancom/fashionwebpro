package com.zj.common.aspect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.zj.common.annotation.CategoryGroup;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.po.SysCategory;
import com.zj.core.service.ISysCategoryService;

@Aspect
public class CategoryValueConvertAspect {
	private static final Logger log = Logger.getLogger(CategoryValueConvertAspect.class);
	@Resource
	private ISysCategoryService categoryService;
	
	public Object convertNameToCode(Object obj){
		if(obj != null){
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field:fields){
				CategoryGroup group = field.getAnnotation(CategoryGroup.class);
				if(group != null){
					try {
						field.setAccessible(true);
						String referenceKey = group.referneceKey();
						String fieldValue = (String) field.get(obj);
						if(fieldValue != null && !"".equals(fieldValue)){
							log.info("convert name to code , fieldValue is "+fieldValue+" , group code is "+referenceKey);
							List<SysCategory> items = categoryService.loadAllItems(referenceKey);
							for(SysCategory item:items){
								if(fieldValue.equalsIgnoreCase(item.getEname()) || fieldValue.equalsIgnoreCase(item.getCname())){
									String categoryCd = item.getCategoryCd();
									field.set(obj, categoryCd);
									break;
								}
							}
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ServiceException e) {
						log.warn("category item is not exist, convert name to code failed!",e);
					}
				}
			}
		}
		return obj;
	}
	public Object convertCodeToName(Object obj){
		if(obj != null){
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field:fields){
				CategoryGroup group = field.getAnnotation(CategoryGroup.class);
				if(group != null){
					try {
						field.setAccessible(true);
						String referenceKey = group.referneceKey();
						String fieldValue = (String) field.get(obj);
						if(fieldValue != null && !"".equals(fieldValue)){
							log.info("convert code to name , fieldValue is "+fieldValue+" , group code is "+referenceKey);
							List<SysCategory> items = categoryService.loadAllItems(referenceKey);
							for(SysCategory item:items){
								if(fieldValue.equalsIgnoreCase(item.getCategoryCd())){
									String categoryEname = item.getEname();
									field.set(obj, categoryEname);
									break;
								}
							}
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ServiceException e) {
						log.warn("category item is not exist, convert name to code failed!",e);
					}
				}
			}
		}
		return obj;
	}
	@Around(value="execution(* com.zj.core.dao.*.get(..))")
	public Object aroundAdviceForGet(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao get Method Start! ================");
		try {
			Object retVal = pjp.proceed();
			retVal = convertCodeToName(retVal);
			return retVal;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao get Method End! ================");
		}
	}
	
	@Around(value="execution(* com.zj.core.dao.*.update(..))")
	public Object aroundAdviceForUpdate(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao update Method Start! ================");
		try {
			Object[] args = pjp.getArgs();
			for(int i=0;i<args.length;i++){
				Object obj = args[i];
				obj = convertNameToCode(obj);
				args[i] = obj;
			}
			Object retVal = pjp.proceed(args);
			return retVal;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao update Method End! ================");
		}
	}
	
	@Around(value="execution(* com.zj.core.dao.*.merge(..))")
	public Object aroundAdviceForMerge(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao merge Method Start! ================");
		try {
			Object[] args = pjp.getArgs();
			for(int i=0;i<args.length;i++){
				Object obj = args[i];
				obj = convertNameToCode(obj);
				args[i] = obj;
			}
			Object retVal = pjp.proceed(args);
			return retVal;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao merge Method End! ================");
		}
	}
	
	@Around(value="execution(* com.zj.core.dao.*.create(..))")
	public Object aroundAdviceForCreate(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao create Method Start! ================");
		try {
			Object[] args = pjp.getArgs();
			for(int i=0;i<args.length;i++){
				Object obj = args[i];
				obj = convertNameToCode(obj);
				args[i] = obj;
			}
			Object retVal = pjp.proceed(args);
			return retVal;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao create Method End! ================");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around(value="execution(* com.zj.core.dao.*.queryHQLForPage(..))")
	public Object aroundAdviceForqueryHQLForPage(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao queryHQLForPage Method Start! ================");
		try {
			PageInfo retVal = (PageInfo) pjp.proceed();
			List objectList = retVal.getObjectList();
			List newList = new ArrayList();
			if(objectList != null){
				for(int i=0;i<objectList.size();i++){
					Object obj = objectList.get(i);
					obj = convertCodeToName(obj);
					newList.add(obj);
				}
			}
			retVal.setObjectList(newList);
			return retVal;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao queryHQLForPage Method End! ================");
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Around(value="execution(* com.zj.core.dao.*.queryHQL(..))")
	public Object aroundAdviceForqueryHQL(ProceedingJoinPoint pjp){
		log.debug("================ Around Advice for dao queryHQL Method Start! ================");
		try {
			List retVal = (List) pjp.proceed();
			List newList = new ArrayList();
			if(retVal != null){
				for(int i=0;i<retVal.size();i++){
					Object obj = retVal.get(i);
					obj = convertCodeToName(obj);
					newList.add(obj);
				}
			}
			return newList;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			log.debug("================ Around Advice for dao queryHQL Method Start! ================");
		}
	}
}
