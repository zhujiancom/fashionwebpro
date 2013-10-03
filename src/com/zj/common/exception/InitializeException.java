package com.zj.common.exception;

import com.zj.common.log.Log;

public class InitializeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951127921830270562L;

	public InitializeException() {
		super();
	}

	public InitializeException(String msg, Throwable cause) {
		super(msg, cause);
		Log.info(this.getClass(), msg);
	}

	public InitializeException(String msg) {
		super(msg);
		Log.info(this.getClass(), msg);
	}
	
}
