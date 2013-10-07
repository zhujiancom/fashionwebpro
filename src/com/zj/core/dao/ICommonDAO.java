package com.zj.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.DAOException;
import com.zj.common.utils.PageInfo;

/**
 * 
 * @author zhujian
 * 通用dao接口，实现每个实体 bean 的 CRUD 操作
 */
public interface ICommonDAO {
	public final String DEFAULTDBNAME = "defaultdb";
	/**
	 * 保存持久化对象
	 * @param obj
	 */
	public <T> void create(T obj) throws DAOException;
	public <T> void create(T obj,String dbname) throws DAOException;
	/**
	 * 批量插入持久化对象
	 * @param objs
	 */
	public <T> void createAll(Collection<T> objs) throws DAOException;
	public <T> void createAll(Collection<T> objs,String dbname) throws DAOException;
	/**
	 * 删除持久化对象
	 * @param obj
	 */
	public <T> void delete(T obj) throws DAOException;
	public <T> void delete(T obj,String dbname) throws DAOException;
	/**
	 * 批量删除持久化对象
	 * @param objs
	 */
	public <T> void deleteAll(Collection<T> objs) throws DAOException;
	public <T> void deleteAll(Collection<T> objs,String dbname) throws DAOException;
	
	@SuppressWarnings("rawtypes")
	public void bulkDelete(Class clazz,final Long[] ids) throws DAOException;
	/**
	 * 更新持久化对象
	 * @param obj
	 */
	public <T> void update(T obj) throws DAOException;
	public <T> void update(T obj,String dbname) throws DAOException;

	/**
	 * 
	 *
	 * Describle(描述)：合并持久化对象
	 *
	 * 方法名称：merge
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-15 下午11:00:03
	 *
	 *
	 * @param <T>
	 * @param obj
	 * @param key
	 * @throws DAOException
	 */
	public <T> void merge(T obj,Serializable key,UpdateMode mode) throws DAOException;
	public <T> void merge(T obj,Serializable key,UpdateMode mode,String dbname) throws DAOException;
	/**
	 * 批量更新持久化对象
	 * @param objs
	 */
	public <T> void updateAll(Collection<T> objs) throws DAOException;
	public <T> void updateAll(Collection<T> objs,String dbname) throws DAOException;
	/**
	 * 根据 identifier 查询数据库对象(使用get方式)
	 * @param key
	 * @return
	 */
	public <T> T get(Class<T> clazz,Serializable key) throws DAOException;
	public <T> T get(Class<T> clazz,Serializable key,String dbname) throws DAOException;
	
	/**
	 * 
	 *
	 * Describle(描述)：根据 identifier 查询数据库对象(使用load方式)
	 *
	 * 方法名称：load
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：Object
	 *
	 * Operate Time:2012-6-19 上午10:06:44
	 *
	 *
	 * @param clazz
	 * @param key
	 * @return
	 * @throws DAOException
	 */
	public <T> T load(Class<T> clazz,Serializable key) throws DAOException;
	public <T> T load(Class<T> clazz,Serializable key,String dbname) throws DAOException;
	/**
	 * 查询指定表的所有对象
	 * @return
	 */
	public <T> List<T> getAll(Class<T> clazz)  throws DAOException;
	public <T> List<T> getAll(Class<T> clazz,String dbname)  throws DAOException;
	
	
	
	/**
	 * 
	 *
	 * Describle(描述)：执行一条SQL更新/删除语句
	 *
	 * 方法名称：executeSql
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2012-6-19 上午10:11:05
	 *
	 *
	 * @param sql
	 * @throws DAOException
	 */
	public void executeSql(String sql) throws DAOException;
    public void executeSql(String sql, String dbname) throws DAOException;
    /**
     * 
     *
     * Describle(描述)：执行一条SQL更新/删除语句
     *
     * 方法名称：executeHql
     *
     * 所在类名：ICommonDAO
     *
     * 返回类型：int
     *
     * Operate Time:2012-6-19 上午11:07:24
     *
     *
     * @param hql
     * @return
     * @throws DAOException
     */
    public int executeHql(String hql) throws DAOException;
    public int executeHql(String hql,String dbname) throws DAOException;
    /**
	 * 
	 *
	 * Describle(描述)：无返回值的存储过程调用 
	 *
	 * 方法名称：executeProcedure
	 *
	 * 所在类名：CommonDAOImpl
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2012-6-19 下午01:47:48
	 *
	 *
	 * @param proc 形如：{call testproc('p1','p2')}
	 */
    public void executeProcedure(String proc) throws DAOException;
    public void executeProcedure(String proc,String dbname) throws DAOException;
    /**
     * 
     *
     * Describle(描述)：有返回值的存储过程调用 (非结果集)
     *
     * 方法名称：executeProcedureReturn
     *
     * 所在类名：ICommonDAO
     *
     * 返回类型：Map<String,Object>
     *
     * Operate Time:2012-6-19 下午02:22:50
     *
     *
     * @param proc
     * @param params key:参数值，value:参数类型
     * @return
     * @throws DAOException
     */
    public <T> String executeProcedureReturn(String proc,Map<String,String> params) throws DAOException;
    public <T> String executeProcedureReturn(String proc,Map<String,String> params,String dbname) throws DAOException;
    /**
	 * 
	 *
	 * Describle(描述)：根据 hql 语句查询数据 
	 *
	 * 方法名称：findByHQL
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：List<Object>
	 *
	 * Operate Time:2012-6-19 上午10:09:54
	 *
	 *
	 * @param hql
	 * @return
	 */
	public <T> List<T> queryHQL(String hql) throws DAOException;
	public <T> List<T> queryHQL(String hql,String dbname) throws DAOException;
	/**
	 * 
	 *
	 * Describle(描述)：根据 sql 语句查询数据 (List中装的是MAP对象)
	 *
	 * 方法名称：querySQL
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：List<Object>
	 *
	 * Operate Time:2012-6-19 上午10:14:06
	 *
	 *
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	public List<Map<String,Object>> querySQL(String sql) throws DAOException;
	public List<Map<String,Object>> querySQL(String sql,String dbname) throws DAOException;
	/**
	 * 
	 *
	 * Describle(描述)：标准 HQL 分页查询
	 *
	 * 方法名称：queryHQLForPage
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：PageInfo
	 *
	 * Operate Time:2012-6-19 上午10:16:56
	 *
	 *
	 * @param hql
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws DAOException
	 */
	public <T> PageInfo<T> queryHQLForPage(String hql,int pageSize,int pageNum) throws DAOException;
//	public PageInfo<T> queryHQL(String hql,int pageSize,int pageNum,String dbname) throws DAOException;
	/**
	 * 
	 *
	 * Describle(描述)：标准sql分页查询
	 *
	 * 方法名称：querySQLForPage
	 *
	 * 所在类名：ICommonDAO
	 *
	 * 返回类型：PageInfo
	 *
	 * Operate Time:2012-8-6 下午05:47:25
	 *
	 *
	 * @param sql
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws DAOException
	 */
	public <T> PageInfo<T> querySQLForPage(String sql,int pageSize,int pageNum) throws DAOException;
//	public PageInfo<T> querySQL(String sql,int pageSize,int pageNum,String dbname) throws DAOException;

	public String generateCode() throws DAOException;
}