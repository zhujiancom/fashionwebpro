package com.zj.business.vo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.zj.bigdefine.CommonConstant;
import com.zj.business.observer.LanguageType;

public class MenuVO extends AbstractVO{
	public static Map<String, String> menuMap;

	public MenuVO() {
		menuMap = new HashMap<String, String>();
		init();
	}

	public void init() {
		menuMap.put(CommonConstant.BRAND, "品牌");
		menuMap.put(CommonConstant.INTERVIEWS, "采访");
		menuMap.put(CommonConstant.VIDEOS, "视频");
		menuMap.put(CommonConstant.AUDIOS, "音频");
		menuMap.put(CommonConstant.PRESSREPORTS, "文字采访");
		menuMap.put(CommonConstant.COLLECTIONS, "作品");
		menuMap.put(CommonConstant.LOOKBOOK_IMAGES, "硬照");
		menuMap.put(CommonConstant.EDITORIAL_IMAGES, "广告画");
		menuMap.put(CommonConstant.RUNWAY_SHOWS, "T台秀");
		menuMap.put(CommonConstant.UNKNOW, "未知");
	}

	@Override
	public void process(LanguageType language) {
		if (LanguageType.EN_US.equals(language)) {
			for (Iterator<Entry<String, String>> it = menuMap.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, String> entry = it.next();
				entry.setValue(entry.getKey());
			}
		} else {
			convertTCSC(menuMap, language);
		}
	}

	@Override
	protected void setEnglishValue() {
		throw new UnsupportedOperationException("upsupport implement this method!");
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		throw new UnsupportedOperationException("upsupport implement this method!");		
	}

}
