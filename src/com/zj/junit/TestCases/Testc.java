package com.zj.junit.TestCases;

import org.junit.Test;



public class Testc {
	@Test
	public void testMap() throws Exception{
		String str = "D:\\Web Develop\\Server\\apache-tomcat-7.0.39\\webapps\\FashionWebSite\\upload\\interview\\video\\1375409663991.mp4";
		String str2 = str.replaceAll("\\\\", "/");
		System.out.println(str2);
//		String str3 = "upload/interview/tet.flv";
//		String str4 = str3.replaceAll("/","\\\\");
//		System.out.println(str4);
	}
	
	@Test
	public void testInt() throws Exception{
		System.out.println(7%4);
		System.out.println(7/4);
	}
}
