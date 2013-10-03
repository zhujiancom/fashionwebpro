package com.zj.bigdefine;


public class SQLBuilder {
	public static final String QUERY_MODULELIST = "SELECT module.MODULE_ID AS id,module.PARENT_MODULE_ID AS pid" +
			",module.MODULE_ENAME AS name,module.SORT_NUM AS sortnum" +
			",module.MODULE_URL AS url \n\t" +
			" FROM SYS_MODULE module \n\t" +
			" WHERE 1=1 and module.PARENT_MODULE_ID={0} AND module.ROLE_ID IN ({1})";
	
	public static final String INIT_CATEGORY_MAP = "SELECT scg.CATEGORY_GROUP_ID, scg.CATEGORY_GROUP_CD,sc.CATEGORY_CD,sc.CNAME,sc.ENAME from SYS_CATEGORY_GROUP scg \n" +
						"LEFT JOIN SYS_CATEGORY sc ON scg.CATEGORY_GROUP_ID = sc.CATEGORY_GROUP_ID";
	
	public static final String UPDATE_CATEGORY_MAP = "SELECT scg.CATEGORY_GROUP_ID, scg.CATEGORY_GROUP_CD,sc.CATEGORY_CD,sc.CNAME,sc.ENAME from SYS_CATEGORY_GROUP scg \n" +
							"LEFT JOIN SYS_CATEGORY sc \n" +
							"ON scg.CATEGORY_GROUP_ID = sc.CATEGORY_GROUP_ID WHERE scg.CATEGORY_GROUP_CD = ''{0}''";
	
	public static final String CATEGORY_FIRST_LEVEL_TREE = "SELECT t.CATEGORY_GROUP_ID AS id,t.CATEGORY_GROUP_CD AS code,0 AS pid,t.ENAME AS name,'true' AS isParent," +
															"t.ENABLE AS isenable,'category_main' AS target FROM SYS_CATEGORY_GROUP t";
	
	public static final String CATEGORY_SECONDARY_TREE = "SELECT t.CATEGORY_ID AS id,t.CATEGORY_GROUP_ID AS pid,t.ENAME AS name,t.ENABLE AS isenable FROM SYS_CATEGORY t WHERE t.CATEGORY_GROUP_ID = {0} ";
}
