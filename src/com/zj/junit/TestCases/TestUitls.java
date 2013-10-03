package com.zj.junit.TestCases;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zj.bigdefine.ReferenceKey;
import com.zj.common.utils.CategoryMap;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.NodeType;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.init.ApplicationInitialize;
import com.zj.core.po.SysUser;

public class TestUitls extends AbstractTestCase {
	private ICommonDAO dao = context.getBean("mysqlDao", ICommonDAO.class);
	
	@Test
	public void testJsonUtil_sendHbmObjectGrid() throws Exception{
		PageInfo<SysUser> pageinfo = dao.queryHQLForPage("from SysUser user where user.loginId != 'admin'", 10, 1);
		String json = JSONUtil.sendHbmObjectGrid(pageinfo);
		System.out.println(json);
	}
	
	@Test
	public void testNodeType() throws Exception{
		NodeType n = NodeType.BRAND;
		System.out.println(n);
	}
	
}
