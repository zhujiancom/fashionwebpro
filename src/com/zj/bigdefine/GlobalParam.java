package com.zj.bigdefine;

public class GlobalParam {
	public static final String LOGIN_USER_SESSION = "login_user_session";
	public static final String LOGIN_ACCOUNT_SESSION = "login_account_session";
	public static final String LOGIN_USER_MODULE_PRIV = "moduleList";
	public static final String BASE_AUTH_TOKEN = "auth_token";
	public static final String BASE_AUTOLOGIN_COOKIE = "authlogin_cookie";
	public static final int ONLINE_STATUS = 1;
	public static final String ONLINE_DESC = "在线";
	public static final int OFFLINE_STATUS = 0;
	public static final String OFFLINE_DESC = "离线";
	public static final String ISENABLE = "isEnable";
	public static final int ENABLE = 1;
	public static final String ENABLE_DESC_CH = "可用";
	public static final String ENABLE_DESC_EN = "Enable";
	public static final int DISABLE = 0;
	public static final String DISABLE_DESC_CH = "不可用";
	public static final String DISABLE_DESC_EN = "Disable";
	//存储过程参数形式，是输入还是输出
	public static final String INPARAM = "IN";
	public static final String OUTPARAM = "OUT";
	//新增 or 更新操作标志 actionFlag
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	
	public static final int YES = 1;
	public static final int NO = 0;
	
	public static final String CATALOG_DB = "fashion";
	public static final String COMM_SEQ = "COMM_SEQ";
	
	public static final String JSONTYPE_DEFAULT = "default";
	public static final String JSONTYPE_ID = "id";
	public static final String JSONTYPE_COLLECTION = "collection";
	public static final String JSONTYPE_REFERENCE = "reference";
}
