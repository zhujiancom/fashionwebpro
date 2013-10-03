package com.zj.core.service.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.log.Log;
import com.zj.common.utils.ClassUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.service.ICommonService;

@Component("commonService")
public class CommonServiceImpl implements ICommonService{
	@Autowired
	protected ICommonDAO dao;

//	public ICommonDAO getDao() {
//		return dao;
//	}
//
//	public void setDao(ICommonDAO dao) {
//		this.dao = dao;
//	}

	/**
	 * 
	 * 
	 * Describle(描述)：通用保存操作
	 * 
	 * 方法名称：insert
	 * 
	 * 所在类名：BaseService
	 * 
	 * 返回类型：void
	 * 
	 * Operate Time:2012-6-23 下午11:53:19
	 * 
	 * 
	 * @param obj
	 */
	@Override
	public <T> void insert(T obj) throws ServiceException {
		try{
			dao.create(obj);
		}catch(Exception e){
			throw new ServiceException();
		}
	}

	/**
	 * 
	 * 
	 * Describle(描述)：通用更新操作
	 * 
	 * 方法名称：update
	 * 
	 * 所在类名：BaseService
	 * 
	 * 返回类型：void
	 * 
	 * Operate Time:2012-6-23 下午11:53:57
	 * 
	 * 
	 * @param obj
	 */
	@Override
	public <T> void update(T obj) throws ServiceException {
		dao.update(obj);
	}

	/**
	 * 通用删除操作
	 */
	@Override
	public <T> void delete(T obj) throws ServiceException {
		dao.delete(obj);
	}

	@Override
	public <T> void bulkDelete(Class<T> clazz,Long[] ids) throws ServiceException {
		dao.bulkDelete(clazz, ids);
	}

	/**
	 * 
	 * 
	 * Describle(描述)：启用
	 * 
	 * 方法名称：enable
	 * 
	 * 所在类名：BaseService
	 * 
	 * 返回类型：void
	 * 
	 * Operate Time:2012-7-31 下午01:38:18
	 * 
	 * 
	 * @param obj
	 * @param fieldname
	 */
	@Override
	public <T> void enable(Class<T> clazz, Serializable pk)
			throws ServiceException {
		String className = clazz.getSimpleName();
//		String id = parsePK(clazz);
		String id = ClassUtil.getPKName(clazz);
		String hql = "update "+className+" set isEnable="+GlobalParam.ENABLE+" where "+id+"="+pk;
		int result = dao.executeHql(hql);
		if(result != 1){
			Log.debug(CommonServiceImpl.class, "enable occured error!");
			throw new ServiceException("enable occured error!");
		}
	}

	/**
	 * 
	 * 
	 * Describle(描述)：disable object
	 * 
	 * 方法名称：disable
	 * 
	 * 所在类名：CommonServiceImpl
	 * 
	 * 返回类型：void
	 * 
	 * Operate Time:2012-7-31 下午01:38:18
	 * 
	 * 
	 * @param obj
	 * @param fieldname
	 */
	@Override
	public <T> void disable(Class<T> clazz,Serializable pk) throws ServiceException{
		String className = clazz.getSimpleName();
//		String id = parsePK(clazz);
		String id = ClassUtil.getPKName(clazz);
		String hql = "update "+className+" set isEnable="+GlobalParam.DISABLE+" where "+id+"="+pk;
		int result = dao.executeHql(hql);
		if(result != 1){
			Log.debug(CommonServiceImpl.class, "disable occured error!");
			throw new ServiceException("disable occured error!");
		}
	}
	
	@Override
	public <T> T select(Class<T> clazz,Serializable pk) throws ServiceException{
		return dao.get(clazz, pk);
	}
	
	@Override
	public void executeSQL(String sql) throws ServiceException{
		dao.executeSql(sql);
	}
	
	@Override
	public <T> PageInfo<T> getBySearch(String hql,int pageSize,int pageNum) throws ServiceException{
		return dao.queryHQLForPage(hql, pageSize, pageNum);
		
	}
	
	@Override
	public <T> List<T> getByHQL(String hql) throws ServiceException{
		return dao.queryHQL(hql);
	}
	@Override
	public <T> List<T> getAll(Class<T> clazz) throws ServiceException{
		return dao.getAll(clazz);
	}
	/**
	 * 唯一性验证
	 */
	@Override
	public <T> Boolean uniquenessCheck(Class<T> clazz,String filedname,Object value) throws ServiceException{
		Boolean isUnique = true;
		String objectName = clazz.getSimpleName();
		StringBuffer hql = new StringBuffer("from "+objectName+" obj where obj."+filedname+"=");
		if(value instanceof String){
			hql.append("'").append(((String) value).trim()).append("'");
		}else{
			hql.append(value);
		}
		List<T> list = dao.queryHQL(hql.toString());
		if(list.size()>0){
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public <T> T get(Class<T> clazz, Serializable key) throws ServiceException {
		T t = dao.get(clazz, key);
		if(t == null){
			throw new ServiceException("the object is not exist in database!");
		}
		return t;
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：将数据库查出的数据转换成json数据格式
	 *
	 * 方法名称：getZTreeNode
	 *
	 * 所在类名：BaseService
	 *
	 * 返回类型：List<ZTreeNode>
	 *
	 * Operate Time:2012-7-23 上午09:54:02
	 *
	 *
	 * @param list
	 * @param leafnode
	 * @return
	 */
	public List<ZTreeNode> getZTreeNode(List<Map<String,Object>> list,ZTreeNode leafnode,ZTreeNode rootnode){
   	List<ZTreeNode> treelist = new LinkedList<ZTreeNode>();
   	for(int i=0;i<list.size();i++){
   		Map<String,Object> map = list.get(i);
//   		Map<String,Object> elsemap = new HashMap<String, Object>();
   		ZTreeNode node = new ZTreeNode();
   		node = (ZTreeNode) ClassUtil.setValue(node, map);
   		treelist.add(node);
   	}
   	if(leafnode != null){
			treelist.add(leafnode);
		}
   	if(rootnode != null){
   		treelist.add(rootnode);
   	}
   	return treelist;
   }

	@Override
	public String generateInteralCode() throws ServiceException {
		return dao.generateCode();
	}

	@Override
	public <T> void merge(T obj, Serializable key,UpdateMode mode) throws ServiceException {
		try{
			dao.merge(obj, key,mode);
		}catch(Exception e){
			throw new ServiceException();
		}
	}
}
