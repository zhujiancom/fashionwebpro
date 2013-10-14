package com.zj.core.dao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.driver.OracleTypes;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.exception.DAOException;
import com.zj.common.log.Log;
import com.zj.common.utils.ClassUtil;
import com.zj.common.utils.PageInfo;
import com.zj.core.po.SequenceTab;

public abstract class CommonDAOImpl implements ICommonDAO {

		// protected HibernateTemplate hibernateDao;
		protected Map<String, HibernateTemplate> hibernateDaos;
		// protected JdbcTemplate jdbcDao;
		protected Map<String, JdbcTemplate> jdbcDaos;

		public <T> void create(T obj) throws DAOException {
			create(obj, DEFAULTDBNAME);
		}

		public <T> void create(T obj, String dbname) throws DAOException {
			Log.info(
					CommonDAOImpl.class,
					"\t >>>>>>>>>> 创建持久化对象到数据库：\n"
							+ ToStringBuilder.reflectionToString(obj,
									ToStringStyle.MULTI_LINE_STYLE));
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				ht.save(obj);
				ht.flush(); // 即时提交
			} catch (DataAccessException e) {
				throw new DAOException(obj.toString(), e);
			}

		}

		public <T> void createAll(Collection<T> objs) throws DAOException {
			createAll(objs, DEFAULTDBNAME);
		}

		public <T> void createAll(Collection<T> objs, String dbname)
				throws DAOException {
			// TODO Auto-generated method stub

		}

		public <T> void delete(T obj) throws DAOException {
			delete(obj, DEFAULTDBNAME);

		}

		public <T> void delete(T obj, String dbname) throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				ht.delete(obj);
				ht.flush(); // 即时提交
			} catch (DataAccessException e) {
				throw new DAOException(obj.toString(), e);
			}

		}

		public <T> void deleteAll(Collection<T> objs) throws DAOException {
			// TODO Auto-generated method stub

		}
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		public void bulkDelete(Class clazz,final Long[] ids) throws DAOException{
			String id = ClassUtil.getPKName(clazz);
			final String hql = "delete "+clazz.getSimpleName()+" where "+id+" in (:ids)";
			HibernateTemplate ht = hibernateDaos.get(DEFAULTDBNAME);
			ht.execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					query.setParameterList("ids", ids);
					return query.executeUpdate();
				}
			});
		}

		public <T> void deleteAll(Collection<T> objs, String dbname)
				throws DAOException {
			// TODO Auto-generated method stub

		}

		public <T> void update(T obj) throws DAOException {
			update(obj, DEFAULTDBNAME);

		}
		
		public <T> void update(T obj, String dbname) throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
//				ht.merge(obj);
				ht.saveOrUpdate(obj);
				ht.flush(); // 即时提交
			} catch (DataAccessException e) {
				throw new DAOException(obj.toString(), e);
			}
		}
		
		public <T> void merge(T obj) throws DAOException{
			merge(obj,DEFAULTDBNAME);
		}
		
		public <T> void merge(T obj, String dbname) throws DAOException{
			HibernateTemplate ht = hibernateDaos.get(dbname);
//			T dbObj = (T) ht.get(obj.getClass(), key);
//			T updateObj = (T) ClassUtil.megerPO(dbObj, obj,mode);
//			ht.update(updateObj);
			ht.merge(obj);
			ht.flush();
			ht.clear();
		}

		public <T> void updateAll(Collection<T> objs) throws DAOException {
			// TODO Auto-generated method stub

		}

		public <T> void updateAll(Collection<T> objs, String dbname)
				throws DAOException {
			// TODO Auto-generated method stub

		}

		public <T> T get(Class<T> clazz, Serializable key) throws DAOException {
			return get(clazz, key, DEFAULTDBNAME);
		}

		public <T> T get(Class<T> clazz, Serializable key, String dbname)
				throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				return (T) ht.get(clazz, key);
			} catch (DataAccessException e) {
				throw new DAOException(clazz.getSimpleName() + "(" + key + ")", e);
			}finally{
				ht.clear();
			}
		}

		public <T> T load(Class<T> clazz, Serializable key) throws DAOException {
			return load(clazz, key, DEFAULTDBNAME);
		}

		public <T> T load(Class<T> clazz, Serializable key, String dbname)
				throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				return (T) ht.load(clazz, key);
			} catch (DataAccessException e) {
				throw new DAOException(clazz.getSimpleName() + "(" + key + ")", e);
			}
		}
		
		public <T> List<T> getAll(Class<T> clazz) throws DAOException {
			return getAll(clazz, DEFAULTDBNAME);
		}

		@SuppressWarnings("unchecked")
		public <T> List<T> getAll(Class<T> clazz, String dbname) throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				return ht.find("from " + clazz.getSimpleName());
			} catch (DataAccessException e) {
				throw new DAOException(clazz.getSimpleName(), e);
			}
		}

		public void executeSql(String sql) throws DAOException {
			executeSql(sql, DEFAULTDBNAME);
		}

		public void executeSql(String sql, String dbname) throws DAOException {
			JdbcTemplate jt = jdbcDaos.get(dbname);
			Log.info(this.getClass(), ">>>> " + sql);
			try {
				jt.execute(sql);
			} catch (DataAccessException e) {
				throw new DAOException(sql, e);
			}
		}

		public int executeHql(String hql) throws DAOException {
			return executeHql(hql, DEFAULTDBNAME);
		}

		public int executeHql(String hql, String dbname) throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				return ht.bulkUpdate(hql);
			} catch (Exception e) {
				throw new DAOException(hql, e);
			}
		}

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
		public void executeProcedure(String proc){
			executeProcedure(proc,DEFAULTDBNAME);
		}
		public void executeProcedure(String proc,String dbname){
			JdbcTemplate jt = jdbcDaos.get(dbname);
			try{
				jt.execute(proc);
			}catch(DAOException e){
				e.printStackTrace();
			}
		}
		
		public <T> String executeProcedureReturn(String proc,Map<String,String>params){
			return executeProcedureReturn(proc,params,DEFAULTDBNAME);
		}
		
		@SuppressWarnings("unchecked")
		public <T> String executeProcedureReturn(String proc,final Map<String,String> params,String dbname){
			StringBuffer procstr = new StringBuffer("{call \t");
			procstr.append(proc).append("(");
			String procstr_last = ")}";
			final Set<String> set = params.keySet();
			for(int i=0;i<set.size();i++){
				if(i<set.size()-1){
					procstr.append("?").append(",");
				}else{
					procstr.append("?");
				}
			}
			procstr.append(procstr_last);
			final String sql = procstr.toString();
			JdbcTemplate jt = jdbcDaos.get(dbname);
			String result = "";
			try{
				result = (String) jt.execute(new CallableStatementCreator() {
					public CallableStatement createCallableStatement(Connection con)
							throws SQLException {
						CallableStatement cs = con.prepareCall(sql);
						Iterator<String> it = set.iterator();
						int count =0;
						while(it.hasNext()){
							count++;
							String paramValue = it.next();
							String paramType = params.get(paramValue);
							if(GlobalParam.OUTPARAM.equalsIgnoreCase(paramType)){
								cs.registerOutParameter(count, OracleTypes.VARCHAR);
							}else{
								cs.setString(count, paramValue);
							}
						}
						return cs;
					}
				}, new CallableStatementCallback<T>() {
					
					public T doInCallableStatement(CallableStatement cs)
							throws SQLException, DataAccessException {
						cs.execute();
						Iterator<String> it = set.iterator();
						int index =0;
						int count = 0;
						while(it.hasNext()){
							count++;
							String paramValue = it.next();
							String paramType = params.get(paramValue);
							if(GlobalParam.OUTPARAM.equalsIgnoreCase(paramType)){
								index = count;
							}
						}
						return (T) cs.getString(index);
					}
				});
			}catch(DAOException e){
				e.printStackTrace();
			}
			return result;
		}

		public <T> List<T> queryHQL(String hql) throws DAOException {
			return queryHQL(hql, DEFAULTDBNAME);
		}

		@SuppressWarnings("unchecked")
		public <T> List<T> queryHQL(String hql, String dbname) throws DAOException {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			try {
				return ht.find(hql);
			} catch (DataAccessException e) {
				throw new DAOException(hql, e);
			}finally{
				ht.clear();
			}
		}

		public List<Map<String,Object>> querySQL(String sql) throws DAOException {
			return querySQL(sql, DEFAULTDBNAME);
		}

		public List<Map<String,Object>> querySQL(String sql, String dbname) throws DAOException {
			JdbcTemplate jt = jdbcDaos.get(dbname);
			try {
				return jt.queryForList(sql);
			} catch (DataAccessException e) {
				throw new DAOException(sql, e);
			}
		}


		
		public <T> PageInfo<T> queryHQLForPage(String hql, int pageSize, int pageNum)
				throws DAOException {
			return queryHQL(hql, pageSize, pageNum, DEFAULTDBNAME);
		}

		private <T> PageInfo<T> queryHQL(String hql, int pageSize, int pageNum,
				String dbname) throws DAOException {
			Long stime = System.currentTimeMillis();
			PageInfo<T> pageinfo = new PageInfo<T>();
			if (pageSize > 1000) {
				throw new DAOException("分页查询异常：" + "单页查询数量过大>1000");
			}
			pageinfo.setPageNum(pageNum);
			pageinfo.setPageSize(pageSize);
			if (pageNum < 1) {
				pageinfo.setPageNum(1);
				pageNum = 1;
				Log.warn(this.getClass(), "分页查询警告：" + "分页页码未设置，默认取第一页");
			}
			if (pageSize < 1) {
				pageinfo.setPageSize(1000);
				pageSize = 1000;
				Log.warn(this.getClass(), "分页查询警告：" + "单页数量未设置，默认取前1000条记录");
			}
			String tmp;
			int orderpoint = hql.toLowerCase().indexOf("order by");
			if (orderpoint > 0) {
				tmp = hql.substring(0, orderpoint - 1);
			} else {
				tmp = hql;
			}
			String totalHql = "select count(*) " + tmp + "";
			List<T> list = queryHQL(totalHql, dbname);
			int rowCount = list.get(0).getClass().getSimpleName().equals("Long") ? ((Long) list
					.get(0)).intValue() : ((Integer) list.get(0)).intValue();
			pageinfo.setRowCount(rowCount);
			pageinfo.setPageCount((rowCount + pageSize) / pageSize);
			if (rowCount % pageSize == 0) {
				pageinfo.setPageCount(pageinfo.getPageCount() - 1);
			}
			// 计算需要返回的行数
			int startn = (pageNum - 1) * pageSize;
			list = getListForPage(hql, startn, pageSize, dbname);
			pageinfo.setObjectList(list);
			double usedtime = (System.currentTimeMillis() - stime) / 1000.0;
			if(usedtime > 3000){
	            Log.warn(this.getClass(),"CommonDAOImpl.queryHQL() used " + usedtime + "s");
	        }
			return pageinfo;
		}

		@SuppressWarnings("unchecked")
		private <T> List<T> getListForPage(final String hql, final int offset,
				final int length, String dbname) {
			HibernateTemplate ht = hibernateDaos.get(dbname);
			List<T> list = ht.executeFind(new HibernateCallback<List<T>>() {
				
				public List<T> doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					query.setFirstResult(offset);
					query.setMaxResults(length);
					List<T> list = query.list();
					return list;
				}
			});
			return list;
		}

		public void setHibernateDaos(Map<String, HibernateTemplate> hibernateDaos) {
			this.hibernateDaos = hibernateDaos;
		}

		public void setJdbcDaos(Map<String, JdbcTemplate> jdbcDaos) {
			this.jdbcDaos = jdbcDaos;
		}

		public <T> PageInfo<T> querySQLForPage(String sql, int pageSize, int pageNum)
				throws DAOException {
			return querySQL(sql,pageSize,pageNum,DEFAULTDBNAME);
		}

		public abstract <T> PageInfo<T> querySQL(String sql, int pageSize, int pageNum,
				String dbname) throws DAOException ;
		
		@Override
		public String generateCode() throws DAOException{
			String code = "";
			String sql = "select max(seq.SEQ_NUM) MAXSEQ from SYS_SEQUENCE_TAB seq";
			List<Map<String,Object>> result = querySQL(sql);
			Long maxSeq = 1000L;
			if(result.get(0).get("MAXSEQ") != null){
				maxSeq = Long.parseLong(result.get(0).get("MAXSEQ").toString());
			}
			Long increasementSeq = maxSeq+1;
			code = "#"+increasementSeq;
			SequenceTab seqTab = new SequenceTab(increasementSeq,code);
			create(seqTab);
			return code;
		}

}
