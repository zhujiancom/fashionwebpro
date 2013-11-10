package com.zj.business.vo;

import java.util.List;
import java.util.Map;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Footer;
import com.zj.common.utils.StringUtil;

public class FooterVO extends AbstractVO{
	private Footer footer;
    private String legalstmt;
    private String aboutus;
    private List<Map<String,String>> links;

    public FooterVO(Footer footer) {
		super();
		this.footer = footer;
	}

	@Override
    protected void setEnglishValue() {
		setAboutus(footer.getAboutus_EN());
		setLegalstmt(StringUtil.getStrFromBlob(footer.getLegalstmt_EN()));
    }

    @Override
    protected void setChineseValue(LanguageType language) {
    	setAboutus(convertTCSC(footer.getAboutus_CH(), language));
    	setLegalstmt(convertTCSC(StringUtil.getStrFromBlob(footer.getLegalstmt_CH()), language));
    }

    public String getLegalstmt() {
        return legalstmt;
    }

    public void setLegalstmt(String legalstmt) {
        this.legalstmt = legalstmt;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }
}
