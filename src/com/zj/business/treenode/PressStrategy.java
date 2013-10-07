package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class PressStrategy extends AbstractMakeDataStrategy {
	private long designerId;
	private long pid;
	private String language;
	
	public PressStrategy(long designerId, long pid ,String language ) {
		super();
		this.designerId = designerId;
		this.pid = pid;
		this.language = language;
	}

	public PressStrategy(long designerId) {
		super();
		this.designerId = designerId;
	}


	public long getDesignerId() {
		return designerId;
	}


	public void setDesignerId(long designerId) {
		this.designerId = designerId;
	}


	@Override
	public List<Map<String, Object>> makedata() {
		if(Language.EN_US.equals(language)){
			return makeEn();
		}else{
			return makeZh();
		}
	}
	
	public List<Map<String, Object>> makeEn(){
		List<Map<String,Object>> datamaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> videoMap = new HashMap<String,Object>();
		videoMap.put("treeid", generateId());
		videoMap.put("pid", pid);
		videoMap.put("isparent", "false");
		videoMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=video");
		videoMap.put("name", "Video");
		videoMap.put("target","mainPanel");
		
		Map<String,Object> audioMap = new HashMap<String,Object>();
		audioMap.put("treeid", generateId());
		audioMap.put("pid", pid);
		audioMap.put("isparent", "false");
		audioMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=audio");
		audioMap.put("name", "Audio");
		audioMap.put("target","mainPanel");
		
		Map<String,Object> reportMap = new HashMap<String,Object>();
		reportMap.put("treeid", generateId());
		reportMap.put("pid", pid);
		reportMap.put("isparent", "false");
		reportMap.put("url", "frontend/report_showReports.action?designer.designerId="+designerId);
		reportMap.put("name", "Press Report");
		reportMap.put("target","mainPanel");
		
		datamaps.add(videoMap);
		datamaps.add(audioMap);
		datamaps.add(reportMap);
		return datamaps;
	}
	
	public List<Map<String, Object>> makeZh(){
		List<Map<String,Object>> datamaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> videoMap = new HashMap<String,Object>();
		videoMap.put("treeid", generateId());
		videoMap.put("pid", pid);
		videoMap.put("isparent", "false");
		videoMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=video");
		videoMap.put("name", "视频");
		videoMap.put("target","mainPanel");
		
		Map<String,Object> audioMap = new HashMap<String,Object>();
		audioMap.put("treeid", generateId());
		audioMap.put("pid", pid);
		audioMap.put("isparent", "false");
		audioMap.put("url", "frontend/interview_showInterviews.action?designer.designerId="+designerId+"&type=audio");
		audioMap.put("name", "音频");
		audioMap.put("target","mainPanel");
		
		Map<String,Object> reportMap = new HashMap<String,Object>();
		reportMap.put("treeid", generateId());
		reportMap.put("pid", pid);
		reportMap.put("isparent", "false");
		reportMap.put("url", "frontend/report_showReports.action?designer.designerId="+designerId);
		reportMap.put("name", "文字采访");
		reportMap.put("target","mainPanel");
		
		datamaps.add(videoMap);
		datamaps.add(audioMap);
		datamaps.add(reportMap);
		return datamaps;
	}

}
