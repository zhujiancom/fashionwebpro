package com.zj.common.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LocationInfo;

public class LoggerImpl implements Logger {

	static {
		Properties p = new Properties();

		try {
			System.out.println(">>>>>>>>>> 初始化Log4j配置...");

			InputStream is = LoggerImpl.class
					.getResourceAsStream("/log4j.properties");
			System.out.println(">>>>>>>>>> Log4j配置文件地址: "
					+ LoggerImpl.class.getResource("/log4j.properties")
							.getPath());

			p.load(is);
			// System.out.println(">>>>>>>>>> Log4J配置文件配置:\n" + p);

			// String fileTarget = p.getProperty("log4j.appender.LOGFILE.File");
			// fileTarget = MessageFormat.format(fileTarget, new Object[] {
			// PlatForm.getSystemPath() });
			// System.out.println(">>>>>>>>>> Log4J日志文件路径: " + fileTarget);

			// p.setProperty("log4j.appender.logfile.file", fileTarget);

			System.out.println(">>>>>>>>>> Log4j配置文件内容:\n" + p);

			PropertyConfigurator.configure(p);

			System.out.println(">>>>>>>>>> 初始化Log4j配置文结束!");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private org.apache.log4j.Logger getLogger(Class clazz) {
		return LogManager.getLogger(clazz);
	}

	public static final String FQCN = "com.zj.common.log.Log";

	private String convertMsg(String msg) {
		StringBuffer convertedMsg = new StringBuffer("");

		LocationInfo locInfo = new LocationInfo(new Throwable(), FQCN);
		String className = locInfo.getClassName().substring(
				locInfo.getClassName().lastIndexOf(".") + 1);
		String methodName = locInfo.getMethodName();
		String lineNumber = locInfo.getLineNumber();
		convertedMsg.append("[").append(className).append(".").append(
				methodName).append(": ").append(lineNumber).append("] - ");
		convertedMsg.append(msg);

		return convertedMsg.toString();
	}

	public void debug(Class clazz, String msg) {
		// TODO Auto-generated method stub
		getLogger(clazz).debug(convertMsg(msg));
	}

	public void error(Class clazz, String msg) {
		// TODO Auto-generated method stub
		getLogger(clazz).error(convertMsg(msg));
	}

	public void error(Class clazz, String msg, Throwable t) {
		// TODO Auto-generated method stub
		error(clazz, convertMsg(msg));
		getLogger(clazz).error("堆栈信息:", t);
	}

	public void info(Class clazz, String msg) {
		// TODO Auto-generated method stub
		getLogger(clazz).info(convertMsg(msg));
	}

	public void warn(Class clazz, String msg) {
		// TODO Auto-generated method stub
		getLogger(clazz).warn(convertMsg(msg));
	}

	public void warn(Class clazz, String msg, Throwable t) {
		// TODO Auto-generated method stub
		warn(clazz, convertMsg(msg));
		getLogger(clazz).error("堆栈信息:", t);
	}

}
