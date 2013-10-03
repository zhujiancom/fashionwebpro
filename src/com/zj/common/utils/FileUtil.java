package com.zj.common.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils {
	public static boolean isDirectory(String directoryPath){
		boolean isDirectory = false;
		directoryPath = (directoryPath == null)?"":directoryPath;
		File directory = new File(directoryPath);
		if(directory.exists() && directory.isDirectory()){
			isDirectory = true;
		}
		return isDirectory;
	}
	
	public static String getRoot(){
		String rootPath = FileUtil.class.getClassLoader().getResource("").getPath();
		return rootPath;
	}
	
	public static void main(String[] args){
		System.out.println(FileUtil.getRoot());
	}
}
