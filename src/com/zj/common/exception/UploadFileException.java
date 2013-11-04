package com.zj.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UploadFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4768132451045457149L;
	private static final Log log = LogFactory.getLog(UploadFileException.class);
	
	public UploadFileException(String msg, Throwable throwable) {
		super(msg,throwable);
	}

	public UploadFileException(String msg) {
		super(msg);
		log.info(msg);
	}

	public UploadFileException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadFileException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
