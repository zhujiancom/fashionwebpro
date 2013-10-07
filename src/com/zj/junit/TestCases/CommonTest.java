package com.zj.junit.TestCases;

import org.junit.Test;


public class CommonTest {
	@Test
	public void testESC(){
		String str = "D:\\Web Develop\\Server\\apache-tomcat-7.0.39\\webapps\\FashionWebSite\\upload\\headImg\\backendUser/1373111779136.bmp";
		str = str.replace('\\', '/');
		System.out.println(str);
	}
}
