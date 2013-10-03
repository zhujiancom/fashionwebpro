package com.zj.common.utils;

import java.sql.Blob;
import java.sql.Clob;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：StringUtil
 *
 * 包名称：com.zj.common.utils
 *
 * Operate Time: 2013-6-7 下午10:22:14
 *
 * remark (备注): the utility for dealing with String
 *
 * 文件名称：StringUtil.java
 *
 */
public class StringUtil extends StringUtils {
	private final static String WRAP[] = { "\r\n", "\r", "\n" }; // wrap
	private final static char SEPARATOR = ',';
	
	/**
	 * 获取一个字符串,null转换为空字符串
	 * 
	 * @param src
	 *            源对象
	 * @return 字符串
	 */
	public static String getStr(Object src) {

		return getStr(src, -1);

	}

	/**
	 * 获取一个一定长度的字符串,null转换为空字符串
	 * 
	 * @param src
	 *            源对象
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String getStr(Object src, int length) {
		if (src == null) {
			return "";
		}

		if (src instanceof String) {
			return (String) src;
		}

		String value = String.valueOf(src);

		return value.length() > length && length != -1 ? value.substring(0,
				length) : value;

	}
	
	/**
	 * 
	 *
	 * Describle(描述)：switch String to Clob
	 *
	 * 方法名称：getClobfromString
	 *
	 * 所在类名：StringUtil
	 *
	 * 返回类型：Clob
	 *
	 * Operate Time:2013-6-7 下午10:28:37
	 *
	 *
	 * @param str
	 * @return
	 */
	public static Clob getClobfromString(String str) {
		Clob clob = null;
		if (str != null && !"".equals(str)) {
			clob = Hibernate.createClob(str);
		}
		return clob;
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：switch String to Blob
	 *
	 * 方法名称：getBlobfromString
	 *
	 * 所在类名：StringUtil
	 *
	 * 返回类型：Blob
	 *
	 * Operate Time:2013-6-7 下午10:29:14
	 *
	 *
	 * @param str
	 * @return
	 */
	public static Blob getBlobfromString(String str) {
		Blob blob = null;
		if (str != null && !"".equals(str)) {
			byte[] b = str.getBytes();
			blob = Hibernate.createBlob(b);
		}
		return blob;
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：switch Clob to String
	 *
	 * 方法名称：fromClob
	 *
	 * 所在类名：StringUtil
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-6-7 下午10:29:42
	 *
	 *
	 * @param clob
	 * @return
	 */
	public static String fromClob(Clob clob) {
		if (clob == null) {
			return "";
		}
		String str = "";
		try {
			str = clob != null ? clob.getSubString(1, (int) clob.length())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 把字符串中的回车符或制表符转换成指定的字符
	 * 
	 * @param str
	 *            字符串
	 * @param wrapStr
	 *            替换字符串
	 * @return 字符串
	 */
	public final static String transWrapToString(String str, String wrapStr) {
		StringBuffer sb = new StringBuffer(str);
		int notCheckedLength = sb.length();
		int start = 0;
		int wrapCount = WRAP.length;
		for (int j = 0; j < wrapCount; j++) {
			notCheckedLength = sb.length();
			start = 0;
			while (notCheckedLength > 0) {
				String temp = sb.substring(start);
				int index = temp.indexOf(WRAP[j]);
				if (index == -1) {
					break;
				}

				notCheckedLength -= (index + WRAP[j].length());
				start += index;
				sb.replace(start, start + WRAP[j].length(), wrapStr);
				start += wrapStr.length();
			}
		}
		return sb.toString();
	}
	
	/**
	 *  截取宽度固定字符串，若字符串宽度大于参数设置，则按照参数截取加上"..."
	 * 
	 * @param string
	 *            待截取字符串
	 * @param startIndex
	 *            截取开始位置
	 * @param width
	 *            宽度（一个汉字宽度为宽度单位）
	 * @return 截取后的字符串
	 */
	public static String getWidthFixedStr(String string, int startIndex,
			int width, boolean htmlStr) {
		if (string == null || string.equals(""))
			return "";
		if (startIndex < 0)
			return "";
		if (width < 0)
			return "";

		char baseChar = ' '; // char = 32
		char topChar = '~'; // char = 126
		char c;
		double tempWidth = 0.0;
		int charIndex = 0;
		string = string.substring(startIndex);
		StringBuffer sb = new StringBuffer();
		String resultStr;

		string = string.replaceAll("&lt;", "<");
		string = string.replaceAll("&gt;", ">");
		string = string.replaceAll("&nbsp;", " ");
		string = string.replaceAll("&quot;", "\"");

		while (charIndex <= string.length() - 1 && tempWidth <= width - 1) {
			c = string.charAt(charIndex);
			if (c >= baseChar && c <= topChar)
				tempWidth += 0.5;
			else
				tempWidth += 1; // 汉字加1
			sb.append(c);
			charIndex++;
		}
		resultStr = sb.toString();
		if (resultStr.length() < string.length()) {
			sb.deleteCharAt(resultStr.length() - 1);
			sb.append("...");
			resultStr = sb.toString();
		}
		if (htmlStr) {
			resultStr = resultStr.replaceAll("<", "&lt;");
			resultStr = resultStr.replaceAll(">", "&gt;");
			resultStr = resultStr.replaceAll(" ", "&nbsp;");
			resultStr = resultStr.replaceAll("\"", "&quot;");
		}
		return resultStr;
	}
	
	public static Long[] convertArray(String str){
		return convertArray(str,SEPARATOR);
	}
	
	public static Long[] convertArray(String str,char separatorChar){
		String[] stringArray = split(str, separatorChar);
		return convertArray(stringArray);
	}
	
	public static Long[] convertArray(String[] str){
		if(str == null || str.length < 1){
			return null;
		}
		Long[] result = new Long[str.length];
		for(int i=0;i<str.length;i++){
			try{
				result[i] = Long.parseLong(str[i]);
			}catch(NumberFormatException e){
				result[i] = 0L;
				continue;
			}
		}
		return result;
	}
}
