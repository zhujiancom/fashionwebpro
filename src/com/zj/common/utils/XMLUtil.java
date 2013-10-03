package com.zj.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.zj.business.po.Interview;

public class XMLUtil {
	private static Document generateDocument(List<Interview> interviews){
		 Document doc = DocumentHelper.createDocument();
		 Element rootElement = doc.addElement("playlist");
		 rootElement.addAttribute("version", "1");
		 rootElement.addAttribute("xmlns", "http://xspf.org/ns/0/");
		 Element trackList = rootElement.addElement("trackList");
		 for(int i=0;i<interviews.size();i++){
			 Interview interview = interviews.get(i);
			 Element track = trackList.addElement("track");
			 Element title = track.addElement("title");
			 title.setText(interview.getInterviewEname());
			 Element location = track.addElement("location");
			 String videoUrl = interview.getInterviewurl();
			 location.setText(videoUrl);
			 Element image = track.addElement("image");
			 String imageUrl = interview.getPoster();
			 image.setText(imageUrl);
		 }
		 return doc;
	}
	
	public static void generateFile(String basePath,String filename,List<Interview> interviews){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		XMLWriter out = null;
		try {
			Document doc = generateDocument(interviews);
			String outputfile = basePath+filename;
			File playlist = new File(outputfile);
			if(!playlist.exists()){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
