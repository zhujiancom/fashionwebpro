package com.zj.common.exception;


public class ServiceException extends Exception {
	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392696378275181383L;

	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(String msg,Throwable throwable){
		super(msg,throwable);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
