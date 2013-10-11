package com.zj.common.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.zj.common.exception.ServiceException;

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
	
	public static File generateDirectory(String dirPath) throws ServiceException{
		File directory = new File(dirPath);
		if(!directory.exists()){
			if(!directory.mkdirs()){
				throw new ServiceException("generate directory error!");
			}
		}
		return directory;
	}
}
