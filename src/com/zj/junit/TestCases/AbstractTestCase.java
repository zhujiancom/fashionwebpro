package com.zj.junit.TestCases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zj.core.po.SysUser;


public class AbstractTestCase {
	protected static ApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		String[] configLocations = new String[]{"spring_conf/applicationContext-beans.xml"};
		context = new ClassPathXmlApplicationContext(configLocations);
	}
	
	public SysUser mockSysUser(String loginId){
		SysUser sysUser = new SysUser(loginId);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sysUser.setPswd("1");
			Date birthday = sdf.parse("1989-10-28");
			sysUser.setBirthday(birthday);
			sysUser.setCname("史玉丹");
			sysUser.setEname("jessica,shi");
			sysUser.setEmail("feifei-yd@163.com");
			sysUser.setSex("0002");
			sysUser.setCreater("admin");
			sysUser.setCreateTime(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sysUser;
	}
	
}
