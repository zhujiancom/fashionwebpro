package com.zj.business.action;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Account;
import com.zj.business.service.IAccountService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;

@Component("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886485568925326220L;
	
	private static final Log log = LogFactory.getLog(AccountAction.class);
	private String referer;
	private Account account;
	@Resource
	private IAccountService accountService;
	
	// parameters from flexgird
	private int rp; // page size
	private int page; // page num
	private String query;
	private String qtype;
	// parameters from flexgird
	private String ids; //account id which need to be deleted
	private Long id; //account id which need to be modified
	
	// image upload relative params
	/*客户端传输的文件信息*/
	private File imageFile;  // 文件名
	private String imageFileContentType;  // 文件名 + ContentType (固定写法)
	private String imageFileFileName;  //文件名 + FileName (固定写法)
	// image upload relative params
	
	/**
	 * 
	 *
	 * Describle(描述)：account login
	 *
	 * 方法名称：login
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-17 下午09:30:23
	 *
	 *
	 * @return
	 */
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ref = request.getHeader("referer");
		setReferer(ref);
		Account login_account;
		try{
			login_account = accountService.login(account);
			if(login_account == null){
				getValueStack().set("loginMsg","Login invalid!");
				return "login_invalid";
			}
			if(GlobalParam.ENABLE != login_account.getIsEnable()){
				getValueStack().set("loginMsg","The Account has been disabled, please reEnable !");
				return "login_failure";
			}
			session.put(GlobalParam.LOGIN_ACCOUNT_SESSION, login_account);
			return "login_success";
		}catch(ServiceException e){
			getValueStack().set("loginMsg","Login Failure!");
			return "login_failure";
		}
	}
	
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ref = request.getHeader("referer");
		setReferer(ref);
		session.remove(GlobalParam.LOGIN_ACCOUNT_SESSION);
		return "logout_success";
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：show all account in background management
	 *
	 * 方法名称：showAllAccounts
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-17 下午09:34:20
	 *
	 *
	 */
	public void showAllAccounts(){
		try{
			PageInfo<Account> pageinfo = accountService.loadAccountList(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		}catch(ServiceException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：search account 
	 *
	 * 方法名称：doSearch
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-17 下午09:36:37
	 *
	 *
	 */
	public void doSearch(){
		try{
			PageInfo<Account> pageinfo = accountService.searchAccountList(rp, page, qtype, query);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		}catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：batch delete Account
	 *
	 * 方法名称：deleteAccounts
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-7-17 下午09:39:53
	 *
	 *
	 */
	public void deleteAccounts(){
		try{
			Long[] keys = StringUtil.convertArray(ids);
			accountService.bulkDelete(Account.class, keys);
			String msg = "Delete ["+keys.length+"] Accounts Successfully!";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (Exception e) {
			String msg = "Delete Users Failure !";
			String json = JSONUtil.stringToJson(msg);
			log.debug(e.getMessage());
			sendJSONdata(json);
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：register account by front user
	 *
	 * 方法名称：registerAccount
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：String
	 *
	 * Operate Time:2013-7-17 下午09:50:52
	 *
	 *
	 * @return
	 */
	public String registerAccount(){
		try{
			account.setCreateTime(new Date());
			accountService.insert(account);
			getValueStack().set("msg", "register account ["+account.getAccountname()+"] successfully!");
			session.put(GlobalParam.LOGIN_ACCOUNT_SESSION, account);
			return "register_account_successful";
		}catch(ServiceException se){
			se.printStackTrace();
			log.debug(se.getMessage());
			getValueStack().set("msg", "register account ["+account.getAccountname()+"] Failure! ");
			return "register_account_failure";
		}
		catch(Exception e){
			getValueStack().set("msg", "register account ["+account.getAccountname()+"] Failure! ");
			log.debug(e.getMessage());
			return "register_account_failure";
		}
	}
	
	/**
	 * 
	 *
	 * Describle(描述)：check the account name unique
	 *
	 * 方法名称：uniqueAccountCheck
	 *
	 * 所在类名：AccountAction
	 *
	 * 返回类型：void
	 *
	 * Operate Time:2013-8-3 下午01:11:00
	 *
	 *
	 */
	public void uniqueAccountCheck(){
		String json = "false";
		try {
			Boolean isUnique = accountService.uniquenessCheck(Account.class, "accountname", account.getAccountname());
			json = JSONUtil.stringToJson(String.valueOf(isUnique));
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		sendJSONdata(json);
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getRp() {
		return rp;
	}
	public void setRp(int rp) {
		this.rp = rp;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	public String getImageFileContentType() {
		return imageFileContentType;
	}
	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}
	public String getImageFileFileName() {
		return imageFileFileName;
	}
	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
}
