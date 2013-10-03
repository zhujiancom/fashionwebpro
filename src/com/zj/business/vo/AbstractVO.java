package com.zj.business.vo;

import com.zj.common.utils.StringUtil;

public abstract class AbstractVO implements Language {
	
	@Override
	public String convertTCSC(String src,String language){
		StringBuffer result = new StringBuffer();
		if(StringUtil.isEmpty(src)){
			return "";
		}
		for(int i=0;i<src.length();i++){
			char currentWord = src.charAt(i);
			int utf8Code = src.codePointAt(i);
			//2616个中文的utf8编码值:繁 min=17079,max=40860, 简 min=13726,max=40863
			if(utf8Code > 13726 && utf8Code < 40863){
				int thisWordPosition = -1;
				if(ZH_TW.equals(language)){
					thisWordPosition = UTF8SC.indexOf(currentWord);
					if(thisWordPosition!= -1){
						result.append(UTF8TC.charAt(thisWordPosition));
					}else{
						result.append(currentWord);
					}
				}else if(ZH_CN.equals(language)){
					thisWordPosition = UTF8TC.indexOf(currentWord);
					if(thisWordPosition!= -1){
						result.append(UTF8SC.charAt(thisWordPosition));
					}else{
						result.append(currentWord);
					}
				}else{
					result.append(currentWord);
				}
			}else{
				result.append(currentWord);
			}
		}
		
		return result.toString();
	}

//	public static void main(String[] args){
//		AbstractVO vo = new AbstractVO();
//		System.out.println(vo.convertTCSC("梦缘", ZH_TW));
//	}
}
