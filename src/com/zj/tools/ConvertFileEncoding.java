package com.zj.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class ConvertFileEncoding {
	public static void convert(String oldFile, String oldCharset, String newFlie, String newCharset){
	      BufferedReader bin;
	      FileOutputStream fos;
	      StringBuffer content = new StringBuffer();
	      try {
	         System.out.println(oldFile);
	         bin = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile), "gbk"));
	         String line = null;
	         while((line=bin.readLine())!=null){
//	          System.out.println("content:" + content);
	            content.append(line);
	            content.append(System.getProperty("line.separator"));
	         }
	         bin.close();
	         File dir = new File(newFlie.substring(0, newFlie.lastIndexOf("\\")));
	         if(!dir.exists()){
	            dir.mkdirs();
	         }
	         fos = new FileOutputStream(newFlie);
	         Writer out = new OutputStreamWriter(fos, newCharset);
	         out.write(content.toString());
	         out.close();
	         fos.close();
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	public static void main(String[] args){
		convert("D:\\Workspace\\repos\\FashionWebSite\\src\\com\\zj\\business\\action\\BrandAction.java", "GBK", "D:\\Workspace\\repos\\FashionWebSite\\src\\com\\zj\\business\\action\\BrandAction.java", "UTF-8");
	}
}
