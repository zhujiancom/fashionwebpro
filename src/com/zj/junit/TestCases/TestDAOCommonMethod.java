package com.zj.junit.TestCases;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.zj.bigdefine.ReferenceKey;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.po.Editorial;
import com.zj.business.po.HomePager;
import com.zj.business.po.Lookbook;
import com.zj.business.po.Style;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.utils.JSONUtil;
import com.zj.core.dao.CommonDAOImpl;
import com.zj.core.dao.ICommonDAO;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysCategoryGroup;
import com.zj.core.po.SysMenu;
import com.zj.core.po.SysModule;
import com.zj.core.po.SysModuleOperation;
import com.zj.core.po.SysRole;
import com.zj.core.po.SysUser;

public class TestDAOCommonMethod extends AbstractTestCase {
	private ICommonDAO dao = context.getBean("mysqlDao", ICommonDAO.class);
	
	////////////////////////////// save method cluster //////////////////////////////////////
	@Test
	public void testSaveSysUser() throws Exception{
		SysUser user = mockSysUser("xiaopangmei");
		
		Set<SysUser> users = new HashSet<SysUser>();
		users.add(user);
		//mock role
		Set<SysRole> roles = new HashSet<SysRole>();
		SysRole role1 = new SysRole("administrater");
		role1.setRoleCd("0001");
		role1.setSysUsers(users);
		SysRole role2 = new SysRole("nomoral");
		role2.setRoleCd("0002");
		role2.setSysUsers(users);
		
		roles.add(role1);
		roles.add(role2);
//		user.setSysRoles(roles);
		
//		dao.create(role1);
//		dao.create(role2);
		dao.create(user);
	}
	
	@Test
	public void testSaveSysRole() throws Exception{
		SysRole role = new SysRole("admin");
		role.setRoleCd(dao.generateCode());
		role.setRoleCname("系统管理员");
		dao.create(role);
		
		SysRole role1 = new SysRole("normal");
		role1.setRoleCd(dao.generateCode());
		role1.setRoleCname("普通管理员");
		dao.create(role1);
	}
	
	@Test
	public void testSaveCategoryGroup() throws Exception{
		SysCategoryGroup group = new SysCategoryGroup(ReferenceKey.SALES_VOLUME);
		group.setCname("销售规模");
		group.setEname("Sales Volume");
		
		SysCategory category1 = new SysCategory(dao.generateCode());
		category1.setCname("低于500万美元");
		category1.setEname("Under 5M USD");
		category1.setParent(group);
		category1.setCreater("Eric, Zhu");
		category1.setCreateTime(new Date());
		
		SysCategory category2 = new SysCategory(dao.generateCode());
		category2.setCname("500万 - 1000万美元");
		category2.setEname("Btwn5 M – 10M USD");
		category2.setParent(group);
		category2.setCreater("Eric, Zhu");
		category2.setCreateTime(new Date());
		
		SysCategory category3 = new SysCategory(dao.generateCode());
		category3.setCname("1000万 - 1500万美元");
		category3.setEname("Btwn 10M – 15M USD");
		category3.setParent(group);
		category3.setCreater("Eric, Zhu");
		category3.setCreateTime(new Date());
		
		SysCategory category5 = new SysCategory(dao.generateCode());
		category5.setCname("1500万 - 2000万美元");
		category5.setEname("Btwn 15M – 20M USD");
		category5.setParent(group);
		category5.setCreater("Eric, Zhu");
		category5.setCreateTime(new Date());
		
		SysCategory category6 = new SysCategory(dao.generateCode());
		category6.setCname("2000万 - 5000万美元");
		category6.setEname("Btwn 20M – 50M USD");
		category6.setParent(group);
		category6.setCreater("Eric, Zhu");
		category6.setCreateTime(new Date());
		
		SysCategory category7 = new SysCategory(dao.generateCode());
		category7.setCname("5000万 - 1亿美元");
		category7.setEname("Btwn 50M – 100M USD");
		category7.setParent(group);
		category7.setCreater("Eric, Zhu");
		category7.setCreateTime(new Date());
		
		SysCategory category4 = new SysCategory(dao.generateCode());
		category4.setCname("大于1亿万美元");
		category4.setEname("Over 100M USD USD");
		category4.setParent(group);
		category4.setCreater("Eric, Zhu");
		category4.setCreateTime(new Date());
		
		Set<SysCategory> childs = new HashSet<SysCategory>();
		childs.add(category1);
		childs.add(category2);
		childs.add(category3);
		childs.add(category4);
		childs.add(category5);
		childs.add(category6);
		childs.add(category7);
		group.setSubCategories(childs);
		group.setCreater("Eric, Zhu");
		group.setCreateTime(new Date());
		
		dao.create(group);
	}
	
	@Test
	public void testSaveModule() throws Exception{
		SysModule module = new SysModule("BackUserAdmin");
		module.setModuleCname("后台账户管理");
		// ensure the role already existed in db
		SysRole role = (SysRole) dao.get(SysRole.class, 1L);
		SysModuleOperation operation1 = new SysModuleOperation("ADD");
		operation1.setOperateCname("新增");
		
		SysModuleOperation operation2 = new SysModuleOperation("DELETE");
		operation2.setOperateCname("删除");
		
		SysModuleOperation operation3 = new SysModuleOperation("UPDATE");
		operation3.setOperateCname("更改");
		
		Set<SysModuleOperation> operations = new HashSet<SysModuleOperation>();
		operations.add(operation1);
		operations.add(operation2);
		operations.add(operation3);
		
		module.setRole(role);
		module.setModuleLevel(2);
		module.setOperations(operations);
		module.setModuleCd(dao.generateCode());
		module.setSortNum(2);
		module.setParentId(1);
		module.setModuleUrl("backend/modules/UserAdmin/back_index.jsp");
		module.setCreater("zj");
		module.setCreateTime(new Date());
		dao.create(module);
	}
	
	@Test
	public void testSaveMenu() throws Exception{
		SysMenu menu = new SysMenu("0001", "Home Page");
		// ensure the role already existed in db
		SysRole role = (SysRole) dao.get(SysRole.class, 2L);
		menu.setMenuLevel(1);
		menu.setMenuUrl("front/homepage");
		menu.setRole(role);
		menu.setSortNum(1);
		dao.create(menu);
	}
	
	@Test
	public void testSaveBrand() throws Exception{
		Brand brand = new Brand();
		brand.setBrandEname("lv");
		
		Designer designer = new Designer();
		designer.setEname("Eric,zhu");
		designer.setGender("男");
		designer.setBornyear("2013-07-12");
		brand.setDesigner(designer);
		dao.create(brand);
	}
	
	@Test
	public void testSaveStyle() throws Exception{
		Style style = new Style();
		style.setStyleEname("style1");
		style.setStyleCname("样式1");
		dao.create(style);
	}
	
	@Test
	public void testSaveBrand2() throws Exception{
		Brand brand = new Brand();
		brand.setBrandEname("Brand1");
		brand.setBrandCname("品牌1");
		Set<Style> styles = new HashSet<Style>();
		Style style = dao.get(Style.class, 1L);
		styles.add(style);
		brand.setStyles(styles);
		dao.create(brand);
	}
	
	@Test
	public void testSaveDesigner() throws Exception{
		Designer designer = new Designer();
		designer.setEname("Jessica,Shi");
		designer.setGender("女");
		designer.setBornyear("1989");
		Set<Brand> brands = new HashSet<Brand>();
		Brand b1 = new Brand();
		b1.setBrandEname("Hermes");
		b1.setEstablishyear("1837");
		b1.setDesigner(designer);
		
		Brand b2 = new Brand();
		b2.setBrandEname("LLL");
		b2.setEstablishyear("2010");
		b2.setDesigner(designer);
		brands.add(b1);
		brands.add(b2);
//		designer.setBrands(brands);
		
		dao.create(designer);
	}
	
	@Test
	public void testSaveBrandManyToMany() throws Exception{
		Brand brand = new Brand();
		brand.setBrandEname("NIKE");
		
		Style s1 = dao.get(Style.class, 1L);
		Style s2 = dao.get(Style.class, 2L);
		
		Set<Style> styles = new HashSet<Style>();
		styles.add(s1);
		styles.add(s2);
		
		brand.setStyles(styles);
		
		dao.create(brand);
		
		
	}
	
	@Test
	public void testSaveEditorial() throws Exception{
		Brand brand = dao.get(Brand.class,1L);
		Editorial e = new Editorial();
		e.setEditorialCname("测试");
		e.setEditorialEname("test");
	}
	
	@Test
	public void testSaveLookBook() throws Exception{
		Brand brand = dao.get(Brand.class,1L);
		Lookbook l = new Lookbook();
		l.setBrand(brand);
		l.setLookbookCname("测试");
		l.setLookbookEname("test");
		l.setLookbookdate(new Date());
		dao.create(l);
	}
	
	@Test
	public void testSaveHomePager() throws Exception{
		HomePager homepager = dao.get(HomePager.class, 1L);
//		List<Designer> designers = new ArrayList<Designer>();
		Set<Designer> designers = new HashSet<Designer>();
		Designer d1 = new Designer();
		d1.setDesignerId(1L);
		
		Designer d2 = new Designer();
		d2.setDesignerId(2L);
		
		designers.add(d1);
		designers.add(d2);
		
		homepager.setDesigners(designers);
		
		dao.update(homepager);
	}
	
	@Test
	public void testReadHomepager() throws Exception{
		HomePager homepager = dao.get(HomePager.class, 1L);
		Set<Designer> designers = homepager.getDesigners();
		for(Designer d: designers){
			System.out.println(d.getEname());
		}
	}
	
	/////////////////////////////////////  query method cluster //////////////////////////////
	@SuppressWarnings("unchecked")
	@Test
	public void testQueryCategoryGroup() throws Exception{
		String hql = "from SysCategoryGroup group where group.categoryGroupCd='"+ReferenceKey.SEX+"'";
		List<SysCategoryGroup> groups = dao.queryHQL(hql);
		if(groups != null && !groups.isEmpty()){
			SysCategoryGroup group = groups.get(0);
			System.out.println(group);
		}
	}
	@Test
	public void testQueryRole() throws Exception{
		String hql = "from SysRole role where role.roleCd='0001'";
		List<SysRole> roles = dao.queryHQL(hql);
		if(roles != null && !roles.isEmpty()){
			SysRole role = roles.get(0);
			System.out.println(role);
		}
	}
	
	@Test
	public void testDesignerFuzzySearch() throws Exception{
		String fuzzyName = "E";
		List<Designer> designers = dao.queryHQL("from Designer designer where designer.ename like '%"+fuzzyName+"%'");
		String json = JSONUtil.listToJson(designers);
		System.out.println(json);
	}
	
	//////////////////////////////////   update method cluster ////////////////////////////
	@Test
	public void testUpdateRole() throws Exception{
		SysRole role = (SysRole) dao.get(SysRole.class, 1L);
		System.out.println(role);
		role.setRoleCname("管理员角色");
		dao.update(role);
	}
	
	@Test
	public void testUpdateUser() throws Exception{
		SysUser user = (SysUser) dao.get(SysUser.class, 1L);
		SysRole role1 = (SysRole) dao.get(SysRole.class, 1L);
		SysRole role2 = (SysRole) dao.get(SysRole.class, 2L);
		Set<SysRole> sysRoles = new HashSet<SysRole>();
		sysRoles.add(role1);
		sysRoles.add(role2);
		user.setSysRoles(sysRoles);
		dao.update(user);
	}
	
	@Test
	public void testUpdateModule() throws Exception{
		SysModule module = (SysModule) dao.get(SysModule.class, 1L);
		module.setSortNum(1);
		module.setModuleLevel(1);
		module.setModuleCname("系统管理");
		module.setModuleCd(dao.generateCode());
		dao.update(module);
	}
	
	@Test
	public void testUpdateBrand() throws Exception{
		Designer designer = dao.get(Designer.class, 8L);
		Set<Brand> brands = designer.getBrands();
		
		Brand brand = new Brand();
		brand.setBrandCname("爱马仕");
		brand.setBrandEname("Hermes");
		brand.setDesigner(designer);
		brands.add(brand);
		dao.create(brand);
	}
	
	@Test
	public void testUpdateBrand2() throws Exception{
		Designer designer = dao.get(Designer.class, 8L);
		Set<Brand> brands = designer.getBrands();
		
		Brand brand = dao.get(Brand.class, 8L);
		brand.setBrandCname("埃尔维");
		brand.setDesigner(designer);
		brands.add(brand);
	}
	
	//////////////////////////////////
	@Test
	public void testGenerateCode() throws Exception{
		CommonDAOImpl daoimpl = (CommonDAOImpl) dao;
		daoimpl.generateCode();
	}
	
	@Test
	public void testBulkDetet() throws Exception{
		Long[] ids = new Long[]{9L,10L};
		dao.bulkDelete(SysUser.class, ids);
	}
}
