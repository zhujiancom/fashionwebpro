package com.zj.common.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public abstract class XmlParse {
	private FileOutputStream fos = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	private XMLWriter out = null;
	
	protected abstract Document generateDocument();
	
	public void generateXMLFile(String outputfile){
		try {
			Document doc = generateDocument();
			File playlist = new File(outputfile);
			if(!playlist.getParentFile().exists() || !playlist.exists()){
				playlist.getParentFile().mkdirs();
				playlist.createNewFile();
			}
			fos = new FileOutputStream(playlist);
			osw = new OutputStreamWriter(fos,"UTF-8");//指定编码，防止写中文乱码
			bw = new BufferedWriter(osw);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			out =  new XMLWriter(bw,format);
			out.write(doc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			 try {
	                if(out != null) {
	                	out.close();
	                }
	                if(bw != null) {
	                    bw.close();
	                }
	                if(osw != null) {
	                    osw.close();
	                }
	                if(fos != null) {
	                    fos.close();
	                }
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
		}
	}
}
