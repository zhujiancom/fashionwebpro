package com.zj.business.converter;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;
import org.hibernate.Hibernate;

public class BlobAndStringConverter extends StrutsTypeConverter {
	private static final Logger logger = Logger.getLogger(BlobAndStringConverter.class);
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		logger.debug("convert String to Blob : "+values[0]);
		Blob result = Hibernate.createBlob(values[0].getBytes());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		if(o instanceof Blob){
			try {
				logger.debug("convert Blob to String .");
				byte[] data = ((Blob) o).getBytes(1, (int) ((Blob) o).length());
				return new String(data);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
