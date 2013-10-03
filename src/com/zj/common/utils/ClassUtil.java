package com.zj.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import com.zj.common.annotation.UpdateColumn;
import com.zj.common.annotation.UpdateMode;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：ClassUtil
 *
 * 包名称：com.zj.common.utils
 *
 * Operate Time: 2013-6-8 上午12:11:18
 *
 * remark (备注):
 *
 * 文件名称：ClassUtil.java
 *
 */
@SuppressWarnings("unchecked")
public class ClassUtil {
	private static final Log log = LogFactory.getLog(ClassUtil.class);

	/**
	 * 返回变量的get方法
	 * 
	 * @param field
	 * @return getter
	 */
	public static String getGetter(String field) {
		String UName = field.substring(0, 1).toUpperCase()
				+ field.substring(1, field.length());
		return ("get" + UName);
	}

	/**
	 * 返回变量的set方法
	 * 
	 * @param field
	 * @return setter
	 */
	public static String getSetter(String field) {
		String UName = field.substring(0, 1).toUpperCase()
				+ field.substring(1, field.length());
		return ("set" + UName);
	}

	/**
	 * 判断指定class中是否存在field变量，并返回
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Field getField(Class clazz, String fieldName) {
		Field f = null;

		Field[] fields = clazz.getDeclaredFields();
		if (fields != null) {
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					f = field;
					f.setAccessible(true);
					break;
				}
			}
		}

		return f;
	}

	/**
	 * 获取类中的所有属性 注：此方法只针对com.zj下面的类，不在此包下的类不做处理 如： class Teacher { String
	 * username; String password; Student student; } class Student { String
	 * username; String password; }
	 * 
	 * List<String> fields = ClassUtil.getAllFields(Teacher.class); fields
	 * 列表中数据：username, password, student.username, student.password
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getAllFields(Class clazz) {
		return getAllFields(clazz, "");
	}

	public static List<String> getAllFields(Class clazz, String parent) {
		List<String> fieldList = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (!"".equals(StringUtil.getStr(parent))) {
				fieldName = StringUtil.getStr(parent) + "." + fieldName;
			}
			fieldList.add(fieldName);

			Class<?> fieldClass = field.getType();
			if (fieldClass.toString().indexOf("com.zj") != -1) {
				fieldList.addAll(getAllFields(field.getType(), fieldName));
				log.debug(field.getName() + "==>" + field.getType());
			}
		}
		return fieldList;
	}

	/**
	 * 判断clazz是否存在method方法，并返回
	 * 
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static Method getMethod(Class clazz, String methodName) {
		Method m = null;

		Method[] ms = clazz.getMethods();
		if (ms != null) {
			for (Method method : ms) {
				if (method.getName() == methodName) {
					m = method;
					break;
				}
			}
		}

		return m;
	}

	/**
	 * 判断clazz是否存在method方法
	 * 
	 * @param clazz
	 * @param method
	 */
	public static boolean haveMethod(Class clazz, String method) {
		boolean flag = false;

		Method[] ms = clazz.getMethods();
		if (ms != null) {
			for (int i = 0; i < ms.length; i++) {
				String n = ms[i].getName();
				if (n == method) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断clazz是否存在method方法
	 * 
	 * @param clazz
	 *            目标类
	 * @param field
	 *            目标类属性
	 */
	public static boolean haveGetterMethod(Class clazz, Field field) {
		boolean flag = false;

		try {
			Method getterMethod = clazz.getDeclaredMethod(getGetter(field
					.getName()), new Class[] {});
			if (getterMethod == null) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 判断clazz是否存在method方法
	 * 
	 * @param clazz
	 *            目标类
	 * @param field
	 *            目标类属性
	 */
	public static boolean haveSetterMethod(Class clazz, Field field) {
		boolean flag = false;
		try {
			Method getterMethod = clazz.getDeclaredMethod(getSetter(field
					.getName()), new Class[] { field.getType() });
			if (getterMethod == null) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 查询obj class的主键字段
	 * 
	 * @param objClazz
	 * @return pk name
	 */
	public static <T> String getPKName(Class<T> objClazz) {
		SessionFactory sf = (SessionFactory) SpringUtils
				.getBean("sessionFactory");
		ClassMetadata md = sf.getClassMetadata(objClazz);
		return md.getIdentifierPropertyName();
	}

	/**
	 * 根据classpath返回符合要求的classList
	 * 
	 * @param classpath
	 *            目标classpath
	 * @return
	 */
	public static List<String> loadclasspach(String classpath) {
		List<String> clazzList = new ArrayList();
		if (classpath == null || classpath.trim().length() == 0) {
			log.warn("获取类失败[classpath is null]");
			return clazzList;
		}
		ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
		SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();
		String[] classpaths = classpath.trim().split(",");
		try {
			for (int i = 0; i < classpaths.length; i++) {
				Resource[] resources = (resourceLoader)
						.getResources(classpaths[i].trim());
				for (int j = 0; j < resources.length; j++) {
					MetadataReader classreader = factory
							.getMetadataReader(resources[j]);
					String classfullname = classreader.getClassMetadata()
							.getClassName();
					clazzList.add(classfullname);
				}
			}
		} catch (Exception e) {
			log.error("获取类失败[classpath=" + classpath + "]");
		}
		return clazzList;

	}

	/**
	 * 存放JAVA基础数据类型在java class bytecode中的对应关系
	 */
	private static HashMap BASE_DATATYPE_MAP;

	static {
		BASE_DATATYPE_MAP = new HashMap();
		BASE_DATATYPE_MAP.put("boolean", "Z");
		BASE_DATATYPE_MAP.put("char", "C");
		BASE_DATATYPE_MAP.put("byte", "B");
		BASE_DATATYPE_MAP.put("short", "S");
		BASE_DATATYPE_MAP.put("int", "I");
		BASE_DATATYPE_MAP.put("float", "F");
		BASE_DATATYPE_MAP.put("double", "D");
		BASE_DATATYPE_MAP.put("long", "J");
	}

	/**
	 * 根据字段类型，返回 java class bytecode中的数据类型，如java.lang.String 对应Ljava/lang/String
	 * ,如int对应I
	 * 
	 * @param typeClass
	 * @return
	 */
	public static String capitalizeType(Class typeClass) {
		String typestr = typeClass.toString();
		if (typeClass.isArray()) {
			return typeClass.getName().replace('.', '/');
		}
		if (BASE_DATATYPE_MAP.containsKey(typestr)) {
			return BASE_DATATYPE_MAP.get(typestr).toString();
		} else {
			return "L" + typeClass.getName().replace('.', '/') + ";";
		}
	}

	/**
	 * 
	 *
	 * Describle(描述)：合并更新bean对象，要求bean对象属性都为基本数据类型的
	 *
	 * 方法名称：megerObject
	 *
	 * 所在类名：ClassUtil
	 *
	 * 返回类型：Object
	 *
	 * Operate Time:2012-8-4 上午01:47:15
	 *
	 *
	 * @param sourceobj
	 * @param distobj
	 * @return
	 */
	public static Object megerObject(Object sourceobj, Object distobj) {
		Class sclass = sourceobj.getClass();
		Class dclass = distobj.getClass();
		if (sclass != dclass) {
			log.info("对象不统一！不能合并");
		} else {
			try {
				List<String> fieldnames = getAllFields(sclass);
				String pkName = getPKName(sclass);
				for (String s : fieldnames) {
					if(s.contains(".")){
						continue;
					}
					Field sfield = getField(sclass, s);
					Field dfield = getField(dclass, s);
					Object dvalue = dfield.get(distobj);
					int flag = dfield.getModifiers();
					//得到该字段的修饰符类型
					String modifiername = Modifier.toString(flag);
					if(dvalue != null && modifiername.indexOf("final") == -1 && !s.equals(pkName)){
						if(dvalue instanceof Integer){
							int value = ((Integer) dvalue).intValue();
							if(value != 0){
								sfield.set(sourceobj, dvalue);
							}
						}else if(dvalue instanceof Long){
							
							long value = ((Long) dvalue).longValue();
							if(value != 0){
								sfield.set(sourceobj, dvalue);
							}
						}else{
							sfield.set(sourceobj, dfield.get(distobj));
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sourceobj;
	}

	@SuppressWarnings("rawtypes")
	public static Object megerPO(Object src, Object dest,UpdateMode mode) {
		Class sclass = src.getClass();
		Class dclass = dest.getClass();
		if (sclass != dclass) {
			log.info("对象不统一！不能合并");
			return null;
		}else{
			switch(mode){
				case MAX:
					src = dest;
					break;
				case NORMAL:
					Field[] fields = sclass.getDeclaredFields();
					for(Field field:fields){
						field.setAccessible(true);
						UpdateColumn column = field.getAnnotation(UpdateColumn.class);
						if(column != null){
							String fieldName = field.getName();
							Field destField = getField(dclass, fieldName);
							destField.setAccessible(true);
							try {
								Object destValue = destField.get(dest);
								int flag = destField.getModifiers();
								//得到该字段的修饰符类型
								String modifiername = Modifier.toString(flag);
								if(destValue != null && modifiername.indexOf("final") == -1){
									field.set(src, destValue);
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case MINI:
					Field[] srcFields = sclass.getDeclaredFields();
					for(Field field:srcFields){
						field.setAccessible(true);
						UpdateColumn column = field.getAnnotation(UpdateColumn.class);
						if(column != null){
							boolean filterColumn = column.filterColumn();
							if(!filterColumn){
								String fieldName = field.getName();
								Field destField = getField(dclass, fieldName);
								destField.setAccessible(true);
								try {
									Object destValue = destField.get(dest);
									int flag = destField.getModifiers();
									//得到该字段的修饰符类型
									String modifiername = Modifier.toString(flag);
									if(destValue != null && modifiername.indexOf("final") == -1){
										field.set(src, destValue);
									}
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}
					}
					break;
			}
			return src;
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：通过反射给一个类的字段赋值
	 *
	 * 方法名称：setValue
	 *
	 * 所在类名：ClassUtil
	 *
	 * 返回类型：Object
	 *
	 * Operate Time:2012-7-23 上午01:14:17
	 *
	 *
	 * @param obj
	 * @param values
	 * @return
	 */
	public static Object setValue(Object obj, Map<String, Object> values) {
		Class clazz = obj.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			
			Iterator<String> it = values.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next().toLowerCase();
				Object value = values.get(key);
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					if (fieldName.toLowerCase().equals(key) && value != null) {
						String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
						Class valueType = fields[i].getType();
						Method m = clazz.getMethod(methodName, valueType);
						m.invoke(obj, value);
					}
					Field f = fields[i];
					f.setAccessible(true);
					Object valueobj = f.get(obj);
					if(valueobj == null){
						f.set(obj,"");
					}
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return obj;
	}
	/**
	 * 
	 *
	 * Describle(描述)：将list中的map数据转成所要的对象
	 *
	 * 方法名称：reCreateObject
	 *
	 * 所在类名：ClassUtil
	 *
	 * 返回类型：List
	 *
	 * Operate Time:2012-7-23 上午01:15:34
	 *
	 *
	 * @param clazz
	 * @param data
	 * @return
	 */
	public static List reCreateObject(Class clazz,List data){
		List list = new LinkedList();
		for(int i=0;i<data.size();i++){
			Map mapdata = (Map) data.get(i);
			try {
				Object obj = clazz.newInstance();
				Iterator<String> it = mapdata.keySet().iterator();
				while(it.hasNext()){
					String key = it.next().toLowerCase();
					Object value = mapdata.get(key);
					Field[] fields = clazz.getDeclaredFields();
					for(int j=0;j<fields.length;j++){
						Field field = fields[j];
						String fieldvaluetype = field.getType().getName();
						if(field.getName().toLowerCase().equals(key)){
							field.setAccessible(true);
							if(value != null){
								if(value instanceof BigDecimal){
									if("long".equals(fieldvaluetype)){
										BigDecimal bd = (BigDecimal)value;
										value = bd.longValue();
									}
									if("java.lang.Long".equals(fieldvaluetype)){
										BigDecimal bd = (BigDecimal)value;
										value = bd.longValue();
									}
									if("int".equals(fieldvaluetype)){
										BigDecimal bd = (BigDecimal)value;
										value = bd.intValue();
									}
									if("java.lang.Integer".equals(fieldvaluetype)){
										BigDecimal bd = (BigDecimal)value;
										value = bd.intValue();
									}
								}
								field.set(obj, value);
							}
						}
					}
				}
				list.add(obj);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	public static <T> T enableOrDisable(T obj,String fieldname,int value){
		Field field = getField(obj.getClass(), fieldname);
		String fieldType = field.getType().getName();
		Object valueobj = new Object();
		if("java.lang.Integer".equals(fieldType) || "int".equals(fieldType)){
			valueobj = Integer.valueOf(value);
		}
		if("java.lang.String".equals(fieldType)){
			valueobj = String.valueOf(value);
		}
		try {
			field.set(obj, valueobj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
