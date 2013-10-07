package com.zj.common.exception;

import com.zj.common.log.Log;

public class UploadFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4768132451045457149L;
	
	public UploadFileException(String msg, Throwable throwable) {
		super(msg,throwable);
		Log.info(this.getClass(), msg);
	}

	public UploadFileException(String msg) {
		super(msg);
		Log.info(this.getClass(), msg);
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
