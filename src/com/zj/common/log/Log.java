package com.zj.common.log;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：Log
 *
 * 包名称：com.zj.common.log
 *
 * Operate Time: 2013-4-20 下午01:06:10
 *
 * remark (备注):The factory for creating a Log instance
 *
 * 文件名称：Log.java
 *
 */
public class Log {
	private static Logger _logger = null;

	public static void debug(Class clazz, String msg) {
		getLogger().debug(clazz, msg);
	}

	public static void error(Class clazz, String msg) {
		getLogger().error(clazz, msg);
	}

	public static void error(Class clazz, String msg, Throwable t) {
		getLogger().error(clazz, msg, t);
	}

	private static Logger getLogger() {
		if (_logger == null) {
			try {
				_logger = (Logger) new LoggerImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return _logger;
	}

	public static void info(Class clazz, String msg) {
		getLogger().info(clazz, msg);
	}

	public static void warn(Class clazz, String msg) {
		getLogger().warn(clazz, msg);
	}

	public static void warn(Class clazz, String msg, Throwable t) {
		getLogger().warn(clazz, msg, t);
	}
}
