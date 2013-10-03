package com.zj.common.log;

/**
 * 日志接口
 * 
 * @author Administrator
 * 
 */
public interface Logger {

	// debug级别信息输出
	public void debug(Class clazz, String msg);

	// error级别信息输出
	public void error(Class clazz, String msg);

	public void error(Class clazz, String msg, Throwable t);

	// info级别信息输出
	public void info(Class clazz, String msg);

	// warn级别信息输出
	public void warn(Class clazz, String msg);

	public void warn(Class clazz, String msg, Throwable t);
}
