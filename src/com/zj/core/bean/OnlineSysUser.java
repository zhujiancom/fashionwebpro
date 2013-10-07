package com.zj.core.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.zj.core.po.SysUser;


public class OnlineSysUser {
private static List<SysUser> onlineSysUsers = new LinkedList<SysUser>();
	
	/**
	 * 
	 * 
	 * Describle(描述)：统计在线人员(添加)
	 * 
	 * 方法名称：addUser
	 * 
	 * 所在类名：onlineSysUsers
	 * 
	 * 返回类型：void
	 * 
	 * Operate Time:2012-7-30 下午11:51:59
	 * 
	 * 
	 * @param obj
	 */
	public static void addUser(SysUser user) {
		// 如果在线会员列表不为空,则迭代该列表,检查当前登录的会员id与列表中的记录id是否一致.
		for(int i=0;i<onlineSysUsers.size();i++){
			// 先判断该会员是否已有session信息保存在统计对象中.若有,把旧的挤掉,再放入新的.
			SysUser temp = onlineSysUsers.get(i);
			long id = temp.getUserId();
			if(user.getUserId() == id){
				onlineSysUsers.remove(i);
			}
		}
		onlineSysUsers.add(user);
	}

	/**
	 * 
	 *
	 * Describle(描述)：统计在线会员(清除) 
	 *
	 * 方法名称：removeUser
	 *
	 * 所在类名：onlineSysUsers
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2012-7-30 下午11:55:27
	 *
	 *
	 * @param user
	 */
	public static void removeUser(SysUser user) {
		onlineSysUsers.remove(user);
	}
	/**
	 * 
	 *
	 * Describle(描述)：获取在线访客的数量 
	 *
	 * 方法名称：getOnlineVistors
	 *
	 * 所在类名：onlineSysUsers
	 *
	 * 返回类型：int
	 *
	 * Operate Time:2012-7-30 下午11:56:12
	 *
	 *
	 * @return
	 */
	public static int getOnlineVistors(){
		return onlineSysUsers.size();
	}
	/**
	 * 
	 *
	 * Describle(描述)：获取在线人员的列表 
	 *
	 * 方法名称：getOnlineUserList
	 *
	 * 所在类名：onlineSysUsers
	 *
	 * 返回类型：List<SysUser>
	 *
	 * Operate Time:2012-7-31 上午12:01:38
	 *
	 *
	 * @return
	 */
	public static List<SysUser> getOnlineUserList(){
		return onlineSysUsers;
	}
	
	public static boolean isOld(SysUser user){
		for(int i=0;i<onlineSysUsers.size();i++){
			SysUser temp = onlineSysUsers.get(i);
			String chinaName = temp.getCname();//保存在统计类中的用户名
			Date logintime = temp.getLoginTime();  //保存在统计类中的用户登录时间
			if(user.getCname().equals(chinaName) && user.getLoginTime().before(logintime)){
				// 如果当前用户的登录时间比统计对象里保存的同名用户的登录时间要早,则返回true  
				return true;
			}
		}
		return false;
	}
}
