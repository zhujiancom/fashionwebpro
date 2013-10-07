package com.zj.junit.TestCases;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zj.core.converter.IConverter;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysCategoryGroup;
import com.zj.core.po.SysModuleOperation;
import com.zj.core.po.SysRole;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysUserService;

public class TestConverter extends AbstractTestCase {
	private ICommonDAO dao = context.getBean("mysqlDao", ICommonDAO.class);
	private ISysUserService userService = context.getBean("userService", ISysUserService.class);
	private IConverter converter = context.getBean("converter", IConverter.class);
	@Test
	public void testSaveUser() throws Exception{
		SysUser po = new SysUser();
		po.setLoginId("1234");
		dao.create(po);
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		SysUser po = new SysUser();
		po.setUserId(1L);
		po.setLoginId("1234");
		dao.delete(po);
	}
	
	@Test
	public void testInsertService() throws Exception{
		SysUser po = new SysUser();
		po.setLoginId("abcdd");
		userService.insert(po);
		
	}
	@Test
	public void testSaveModuleOperation() throws Exception{
		SysModuleOperation smo = new SysModuleOperation("123");
		dao.create(smo);
	}
	@Test
	public void testSaveRole() throws Exception{
		SysRole role = new SysRole("administrator");
		dao.create(role);
	}
	
	@Test
	public void testSaveCategory() throws Exception{
		SysCategoryGroup group = new SysCategoryGroup("YES/NO");
		group.setCname("是否");
		group.setEname("yse/no");
		
		SysCategory category1 = new SysCategory("0001");
		category1.setCname("是");
		category1.setParent(group);
		
		SysCategory category2 = new SysCategory("0002");
		category2.setCname("否");
		category2.setParent(group);
		
		Set<SysCategory> childs = new HashSet<SysCategory>();
		childs.add(category1);
		childs.add(category2);
		
		group.setSubCategories(childs);
		
//		dao.create(group);
		dao.delete(dao.get(SysCategoryGroup.class, 4L));
		
		
	}
	
	@Test
	public void testConverter() throws Exception{
//		SysUser user = new SysUser();
//		user.setSex("0002");
//		user.setCname("朱健");
//		user.setLoginId("administrator");
//		user.setRegisteTime(new Date());
//		SysUserDTO userDTO = new SysUserDTO();
////		userDTO.setGender("男");
//		converter.convert(user, userDTO,true);
//		System.out.println(userDTO);
	}
	
	@Test
	public void testSaveUserRole() throws Exception{
		
	}
}
