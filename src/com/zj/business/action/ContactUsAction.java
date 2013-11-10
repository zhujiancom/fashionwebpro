package com.zj.business.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.business.po.ContactUs;
import com.zj.business.service.IContactUsService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.JSONUtil;
import com.zj.common.utils.PageInfo;
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
	
	public void showAll(){
		try {
			PageInfo<ContactUs> pageinfo = contactService.loadContactUsForPage(rp, page);
			String json = JSONUtil.sendHbmObjectGrid(pageinfo);
			sendJSONdata(json);
		} catch (ServiceException e){
			e.printStackTrace();
		}
			
	}
	
	public String submitMsg(){
		String msg = "";
		try {
			contactService.insert(contactus);
			msg = getText("contact.message.result.success");
		} catch (ServiceException e) {
			e.printStackTrace();
			msg = getText("contact.message.result.failure");
		}
		getValueStack().set("msg", msg);
		return "submit_message";
	}
	
	public String showMsg(){
		String message = "";
		try {
			ContactUs cu = contactService.get(ContactUs.class, id);
			if(cu != null){
				message = cu.getMessage();
			}
		} catch (ServiceException e) {
			message = e.getMessage();
		}
		getValueStack().set("message", message);
		return "show_msg";
	}
	
	public void handleMsg(){
		try {
			ContactUs cu = contactService.get(ContactUs.class, id);
			if(cu != null){
				cu.setHandled(true);
				contactService.update(cu);
				String msg = JSONUtil.stringToJson("disposal this message complete!");
				sendJSONdata(msg);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
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
