package com.zj.business.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.business.po.ContactUs;
import com.zj.business.service.IContactUsService;
import com.zj.common.exception.ServiceException;
import com.zj.common.log.Log;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;

@Component("contactAction")
@Scope("prototype")
public class ContactUsAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2988417506673150076L;
	private ContactUs contactus;
	@Resource
	private IContactUsService contactService;
	private String ids; // users id which need to be deleted
	private long id; // user id which need to be modify
	private String query;
	private String qtype;
	private int rp; // page size
	private int page; // page num
	
	public String add(){
		try {
			contactService.insert(contactus);
			getValueStack().set("msg", "create Contacter ["+contactus.getContactName()+"] Successfully! ");
			return "save_success";
		} catch (ServiceException e) {
			e.printStackTrace();
			getValueStack().set("msg", "create Contacter ["+contactus.getContactName()+"] Failure! ");
			return "save_failure";
		}
	}
	
	public void delete(){
		try {
			Long[] keys = StringUtil.convertArray(ids);
			contactService.bulkDelete(ContactUs.class, keys);
			String msg = "Delete[" + keys.length + "] Contacters Successfully !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
		} catch (ServiceException e) {
			String msg = "Delete Contacters Failure !";
			String json = JSONUtil.stringToJson(msg);
			sendJSONdata(json);
			Log.debug(DesignerAction.class, e.getMessage());
		}
	}
	
	public String modifyForward(){
		try {
			contactus = contactService.get(ContactUs.class, id);
			getValueStack().setValue("designer", contactus);
			return "modify_forward_successful";
		} catch (ServiceException e) {
			e.printStackTrace();
			Log.debug(BrandAction.class, "The Contacter is not exist!");
			return "modify_forward_failure";
		}
	}
	
	public String update(){
		try {
			contactService.update(contactus);
			getValueStack().set("msg", "update Contacter ["+contactus.getContactName()+"] Successfully! ");
			return "modify";
		} catch (ServiceException se) {
			getValueStack().set("msg", "update Contacter ["+contactus.getContactName()+"] failure!");
			se.printStackTrace();
			Log.debug(StyleAction.class, se.getMessage());
			return "modify";
		}
	}
	
	public void showAll(){
		try {
			PageInfo<ContactUs> pageinfo = contactService.loadContactUsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
			
	}
	
	public String loadInfo(){
		try {
			List<ContactUs> list =  contactService.getAll(ContactUs.class);
			getValueStack().set("contactlist",list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "load_success";
	}
	
	public ContactUs getContactus() {
		return contactus;
	}

	public void setContactus(ContactUs contactus) {
		this.contactus = contactus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
