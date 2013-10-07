package com.zj.common.utils;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;

import java.util.Map;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：SpringUtils
 *
 * 包名称：com.zj.common.utils
 *
 * Operate Time: 2013-6-8 上午12:10:51
 *
 * remark (备注):
 *
 * 文件名称：SpringUtils.java
 *
 */
public class SpringUtils implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static ApplicationContext staticapplicationcontext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        SpringUtils.staticapplicationcontext = applicationContext;
    }

    public static Object getBean(String name) {
        if (SpringUtils.staticapplicationcontext != null) {
            return SpringUtils.staticapplicationcontext.getBean(name);
        }
        return null;

    }

    public static Map getBeansForType(Class clazz){
        if(SpringUtils.staticapplicationcontext!=null){
            return SpringUtils.staticapplicationcontext.getBeansOfType(clazz);
        }
        return null;
    }
}
