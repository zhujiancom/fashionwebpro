package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.business.po.Brand;
import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class PressForBrandStrategy extends AbstractMakeDataStrategy {
	private Brand brand;
	private long pid;
	private String language;
	
	public PressForBrandStrategy(Brand brand,long pid,String language) {
		super();
		this.brand = brand;
		this.pid = pid;
		this.language = language;
	}


	@Override
	public List<Map<String, Object>> makedata() {
		long designerId = brand.getDesigner().getDesignerId();
		List<Map<String,Object>> datamaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> videoMap = new HashMap<String,Object>();
		videoMap.put("treeid", generateId());
		videoMap.put("pid", pid);
		videoMap.put("isparent", "false");
		videoMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=video");
		videoMap.put("target","mainPanel");
		
		Map<String,Object> audioMap = new HashMap<String,Object>();
		audioMap.put("treeid", generateId());
		audioMap.put("pid", pid);
		audioMap.put("isparent", "false");
		audioMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=audio");
		audioMap.put("target","mainPanel");
		
		Map<String,Object> reportMap = new HashMap<String,Object>();
		reportMap.put("treeid", generateId());
		reportMap.put("pid", pid);
		reportMap.put("isparent", "false");
		reportMap.put("url", "frontend/report_showReports.action?designer.designerId="+designerId);
		reportMap.put("target","mainPanel");
		
		if(Language.EN_US.equals(language)){
			videoMap.put("name", "Video");
			audioMap.put("name", "Audio");
			reportMap.put("name", "Press Report");
		}else{
			videoMap.put("name", "视频");
			audioMap.put("name", "音频");
			reportMap.put("name", "文字采访");
		}
		
		datamaps.add(videoMap);
		datamaps.add(audioMap);
		datamaps.add(reportMap);
		return datamaps;
	}

}
