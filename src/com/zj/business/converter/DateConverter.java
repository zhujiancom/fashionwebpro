package com.zj.business.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static final Logger logger = Logger.getLogger(DateConverter.class);
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN_EN = "yyyy/MM/dd";
	private static final String DATETIME_PATTERN_EN = "yyyy/MM/dd HH:mm:ss";

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toType) {
		Object result = null;   
		String value = values[0];
        if (toType == Date.class) {
            try {    
                result = DateUtils.parseDate((String) value, new String[] { DATE_PATTERN, DATETIME_PATTERN,DATE_PATTERN_EN,DATETIME_PATTERN_EN });   
            } catch (ParseException e) {  
            	logger.debug("input date value = "+value);
                e.printStackTrace();   
                logger.debug(e);
            }    
        }    
        return result;  
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object value) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN);
		String result = null;    
		if (value instanceof Date) {    
           result = simpleDateFormat.format(value);    
		}    
		return result;
	} 
}
