package com.zj.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.ZTreeNode;

public interface ICommonService {
	public <T> void insert(T obj) throws ServiceException;
	
	public <T> void update(T obj) throws ServiceException;
	
	public <T> void merge(T obj,Serializable key,UpdateMode mode) throws ServiceException;
	
	public <T> void delete(T obj) throws ServiceException;
	
	public <T> void bulkDelete(Class<T> clazz,Long[] ids) throws ServiceException;
	
	public <T> void disable(Class<T> clazz,Serializable pk) throws ServiceException;
	
	public <T> void enable(Class<T> clazz,Serializable pk) throws ServiceException;
	
	public <T> T select(Class<T> clazz,Serializable pk) throws ServiceException;
	
	public void executeSQL(String sql) throws ServiceException;
	
	public <T> PageInfo<T> getBySearch(String hql,int pageSize,int pageNum) throws ServiceException;
	
	public <T> List<T> getByHQL(String hql) throws ServiceException;
	
	public <T> List<T> getAll(Class<T> clazz) throws ServiceException;
	
	public <T> T get(Class<T> clazz,Serializable key) throws ServiceException;

	public <T> Boolean uniquenessCheck(Class<T> clazz,String filedname,Object value) throws ServiceException;

	public String generateInteralCode() throws ServiceException;
	
	public List<ZTreeNode> getZTreeNode(List<Map<String,Object>> list,ZTreeNode leafnode,ZTreeNode rootnode);
}
