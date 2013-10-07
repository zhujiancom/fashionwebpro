package com.zj.common.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.DisplayName;
import com.zj.common.annotation.JsonData;
import com.zj.common.log.Log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	public static final String LOWER_FORMAT = "lower";  //小写格式     loginuser
	public static final String UPPER_FORMAT = "upper";    //大写格式   LOGINUSER
	
	@SuppressWarnings("rawtypes")
	public static List convertCase(List list){
		return convertCase(list,JSONUtil.LOWER_FORMAT);
	}
	/**
	 * 
	 *
	 * Describle(描述)：由于从数据库读出的列表都是map类型，而且map类型中所有的key都会转化成大写
	 * 	为了符合json字符串的大小写格式，故需要转换,默认使用小写
	 *  同时 如果该键对应的值为空的话，直接转换成    ""
	 *  
	 *
	 * 方法名称：convertCase
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：List
	 *
	 * Operate Time:2012-7-17 上午10:34:01
	 *
	 *
	 * @param list
	 * @param format
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertCase(List list,String format){
		List resultList = new LinkedList();
		for(int i =0;i<list.size();i++){
			Map map = (Map) list.get(i);
			Iterator<String> it = map.keySet().iterator();
			Map resultMap = new HashMap();
			while(it.hasNext()){
				String key = it.next();
				Object value = map.get(key);
				if(value == null){
					value = StringUtil.getStr(value);
				}
				if(format.equals(JSONUtil.LOWER_FORMAT)){
					key = StringUtil.lowerCase(key);
				}
				if(format.equals(JSONUtil.UPPER_FORMAT)){
					key = StringUtil.upperCase(key);
				}
				resultMap.put(key, value);
			}
			resultList.add(resultMap);
		}
		
		return resultList;
	}
	/**
	 * 
	 *
	 * Describle(描述)：将list对象转换成JSONArray对象，同时传入key的值，转换成前台想要的json字符串
	 *
	 * 方法名称：sendArray
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：JSONArray
	 *
	 * Operate Time:2012-7-17 下午02:45:35
	 *
	 *
	 * @param list
	 * @param newkeys
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONArray sendArray(List list,String[] newkeys){
		JSONArray jsonarray = null;
		List newlist = new LinkedList();
		if(newkeys != null){
			for(int i=0;i<list.size();i++){
				Map srcmap = (Map) list.get(i);
				Map<String,Object> destmap = new HashMap<String,Object>();
				Iterator<String> it = srcmap.keySet().iterator();
				while(it.hasNext()){
					String key = it.next();
					Object value = srcmap.get(key);
					for(int j=0;j<newkeys.length;j++){
						if(key.equalsIgnoreCase((newkeys[j]))){
							key = newkeys[j];
							break;
						}
					}
					destmap.put(key, value);
				}
				newlist.add(destmap);
			}
			jsonarray = JSONArray.fromObject(newlist);
		}else{
			jsonarray = JSONArray.fromObject(list);
		}
		return jsonarray;
	}
	/**
	 * 
	 *
	 * Describle(描述)：将一个表单数据封装成指定json字符串输出
	 *
	 * 方法名称：sendObjectForm
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：JSONObject
	 *
	 * Operate Time:2012-7-17 下午03:40:53
	 *
	 *
	 * @param data
	 * @param isError
	 * @param msg
	 * @return
	 */
	public static JSONObject sendObjectForm(Object data,boolean isError,String msg){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("Data",data);
		map.put("IsError",isError);
		map.put("Message", msg);
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	/**
	 * 
	 *
	 * Describle(描述)：返回flexigrid前台json数据格式,jdbc 查询结果
	 * 	格式：{"page":1,"total":14, "rows":[{"id":"0","cell":["0","核桃","坚果","否","admin"]},{"id":"1","cell":["1","核桃","坚果","否","admin"]}]}
	 *
	 * 方法名称：sendObjectGrid
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：JSONObject
	 *
	 * Operate Time:2012-8-6 下午06:11:21
	 *
	 *
	 * @param data
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONObject sendJdbcObjectGrid(PageInfo pageinfo){
		List convertdata = convertCase(pageinfo.getObjectList());
		List rowsList = new LinkedList();
		for(int i=0;i<convertdata.size();i++){
			Map<String,Object> rows = new HashMap<String, Object>();
			rows.put("id", i);
			rows.put("cell", convertdata.get(i));
			rowsList.add(rows);
		}
		Map resultMap = new HashMap();
		resultMap.put("page", pageinfo.getPageNum());
		resultMap.put("total", pageinfo.getRowCount());
		resultMap.put("rows", rowsList);
		JSONObject json = JSONObject.fromObject(resultMap);
		return json;
	}
	
	@SuppressWarnings("rawtypes")
	public static String sendHbmObjectGrid(PageInfo pageinfo){
		String json = sendHbmObjectGrid(pageinfo,null);
		return json;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String sendHbmObjectGrid(PageInfo pageinfo,Map<String,String> otherAttr){
		List objectList = pageinfo.getObjectList();
		List rowsList = new LinkedList();
		for(int i=0;i<objectList.size();i++){
			Map<String,Object> rows = new HashMap<String, Object>();
			Object obj = objectList.get(i);
			rows.put("id", i);
			String cell = objectToJson(obj);
			StringBuffer sb = new StringBuffer();
			if(otherAttr != null){
				String preCell = cell.substring(0,cell.length()-1);
				sb = new StringBuffer(preCell);
				Iterator<String> it = otherAttr.keySet().iterator();
				while(it.hasNext()){
					sb.append(",");
					String key = it.next();
					String value = otherAttr.get(key);
					sb.append("\"").append(key).append("\"").append(":").append("\"").append(value).append("\"");
				}
				cell = sb.append("}").toString();
			}
			rows.put("cell",cell);
			String jsonrows = mapToJson(rows);
			rowsList.add(jsonrows);
		}
		Map resultMap = new HashMap();
		resultMap.put("page", pageinfo.getPageNum());
		resultMap.put("total", pageinfo.getRowCount());
		resultMap.put("rows", rowsList);
		String json = mapToJson(resultMap);
		return json;
	}
	
	/**
	 * 将任意对象转换成json字符串
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object){
		StringBuffer json = new StringBuffer();
		if(object == null){
			json.append("\"\"");
		}else if(object instanceof String){
			String value = object.toString();
			if(value.indexOf('\\') != -1){
				value = value.replace('\\', '/');
			}
			json.append("\"").append(value).append("\"");
		}else if(object instanceof Integer || object instanceof Double || object instanceof Long){
			json.append(object.toString());
		}else if(object instanceof Date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(object);
			json.append("\"").append(date).append("\"");
		}else{
			json.append(beanToJson(object));
		}
		return json.toString();  
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：传入任意一个 javabean 对象生成一个指定规格的字符串 
	 *
	 * 方法名称：beanToJson
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-13 下午03:46:20
	 *
	 *
	 * @param bean
	 * @param isReference : determine the bean whether a reference object, default value is false
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String beanToJson(Object bean){
		Log.info(JSONUtil.class, "Transfer a arbitrarily javabean to generate a specific json string ");
		StringBuilder json = new StringBuilder();  
        json.append("{");  
        PropertyDescriptor[] props = null;
        try {
			props = Introspector.getBeanInfo(bean.getClass(),Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (props != null) {  
            for (int i = 0; i < props.length; i++) {  
                try { 
                	Method method = props[i].getReadMethod();
                	JsonData jsonData = method.getAnnotation(JsonData.class);
                	if(jsonData != null){
                		Object nameobj = props[i].getName();
                    	String name = objectToJson(nameobj);
                    	String value = objectToJson(nameobj);
                		String type = jsonData.type();
                		if(GlobalParam.JSONTYPE_DEFAULT.equals(type)){
                			Object valueobj = method.invoke(bean);
                			value = objectToJson(valueobj);
                		}
                		if(GlobalParam.JSONTYPE_REFERENCE.equals(type)){// the current object reference other object
                			Object valueobj = method.invoke(bean);
                			if(valueobj != null){
                				value = referenceBeanToJson(valueobj);
                			}
                		}
                		if(GlobalParam.JSONTYPE_ID.equals(type)){
                			name = objectToJson("id");
                			Object valueobj = method.invoke(bean);
                			value = objectToJson(valueobj);
                		}
                		if(GlobalParam.JSONTYPE_COLLECTION.equals(type)){
                			Collection valueobj = (Collection)method.invoke(bean);
                			value = collectionToJson(valueobj);
                		}
                		json.append(name);  
                        json.append(":");  
                        json.append(value);  
                        json.append(",");  
                	}
                } catch (Exception e) { 
                	e.printStackTrace();
                }  
            }  
            json.setCharAt(json.length() - 1, '}');  
        }else{
        	json.append("}");
        }
		Log.info(JSONUtil.class, "json = "+json.toString());
		return json.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String collectionToJson(Collection data){
		StringBuilder json = new StringBuilder();
		json.append("\"");
		Iterator it = data.iterator();
		while(it.hasNext()){
			Object obj = it.next();
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field : fields){
				DisplayName displayName = field.getAnnotation(DisplayName.class);
				if(displayName != null){
					field.setAccessible(true);
					try {
						String value = (String) field.get(obj);
						json.append(value).append(",");
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		if(json.length()>1){
			json.setCharAt(json.length() - 1, '\"');  
		}else{
			json.append("\"");
		}
		return json.toString();
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：transfer refernece object value to json string
	 *
	 * 方法名称：referenceBeanToJson
	 *
	 * 所在类名：JSONUtil
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-13 下午05:01:54
	 *
	 *
	 * @param obj
	 * @return
	 */
	public static String referenceBeanToJson(Object obj){
		StringBuilder json = new StringBuilder();
		json.append("\"");
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields){
			DisplayName displayName = field.getAnnotation(DisplayName.class);
			if(displayName != null){
				field.setAccessible(true);
				try {
					String value = (String) field.get(obj);
					json.append(value).append(",");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(json.length()>1){
			json.setCharAt(json.length() - 1, '\"');  
		}else{
			json.append("\"");
		}
		return json.toString();
	}
	
	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 
	 * @param list 列表对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String listToJson(Collection list){
		 StringBuilder json = new StringBuilder();  
	        json.append("[");  
	        if (list != null && list.size() > 0) {  
	            for (Object obj : list) {  
	                json.append(objectToJson(obj));  
	                json.append(",");  
	            }  
	            json.setCharAt(json.length() - 1, ']');  
	        } else {  
	            json.append("]");  
	        }  
	        return json.toString();  
	}
	
	public static String mapToJson(Map<String,Object> map){
		StringBuilder json = new StringBuilder();
		json.append("{");
		if(map != null){
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				Object value = map.get(key);
				json.append("\"").append(key).append("\"").append(":");
				if(value.toString().indexOf("[")!=-1 || value.toString().indexOf("{")!=-1 || value instanceof Integer){
					json.append(value);
				}else{
					json.append("\"").append(value).append("\"");
				}
				json.append(",");
			}
			json.setCharAt(json.length()-1,'}');
		}else{
			json.append("}");
		}
		return json.toString();
	}
	
	public static String stringToJson(String msg){
		Map<String,String> msgmap = new HashMap<String, String>();
		msgmap.put("msg", msg);
		return JSONObject.fromObject(msgmap).toString();
	}
}
