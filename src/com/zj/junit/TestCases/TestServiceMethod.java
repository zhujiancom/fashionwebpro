package com.zj.junit.TestCases;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.init.ApplicationInitialize;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysModule;
import com.zj.core.po.SysRole;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysCategoryService;
import com.zj.core.service.ISysModuleService;
import com.zj.core.service.ISysUserService;

public class TestServiceMethod extends AbstractTestCase {
	@SuppressWarnings("rawtypes")
	private ICommonDAO dao = context.getBean("mysqlDao", ICommonDAO.class);
	private ISysUserService userService = context.getBean("userService",ISysUserService.class);
	private ISysModuleService moduleService = context.getBean("moduleService",ISysModuleService.class);
	private ISysCategoryService categoryService = context.getBean("categoryService", ISysCategoryService.class);
	
	@Test
	public void testLogin() throws Exception{
//		SysUserDTO userDTO = new SysUserDTO("zhujiancom");
//		userDTO.setPswd("123456");
//		userService.login(userDTO);
	}
	
	@Test
	public void testLoadModules() throws Exception{
		SysUser user = (SysUser) dao.get(SysUser.class, 1L);
		Collection<SysRole> roles = user.getSysRoles();
//		List<SysModule> modules = moduleService.loadModulesByRoles(roles);
//		for(SysModule module:modules){
//			System.out.println(module);
//		}
		List<ZTreeNode> moduleList = moduleService.loadModulesByRoles(roles, 0L);
		for(ZTreeNode node:moduleList){
			System.out.println(node);
		}
	}
	
	@Test
	public void testCategoryTreelist() throws Exception{
		List<ZTreeNode> result = categoryService.getCategoryTreeList(0);
		for(ZTreeNode node:result){
			System.out.println(node.toString());
		}
	}
	
	@Test
	public void testCategoryLoadItems() throws Exception{
//		PageInfo<SysCategory> items = categoryService.loadCategoryItemList(10, 1, 1);
//		System.out.println(items);
	}
	
}
