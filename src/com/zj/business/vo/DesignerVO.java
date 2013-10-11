package com.zj.business.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Designer;
import com.zj.common.utils.StringUtil;

public class DesignerVO extends AbstractVO{
	private Designer designer;
	private long id;
	private String name;
	private String profile;
	private String thumbnailUrl;

	public DesignerVO() {
	}

	public DesignerVO(Designer designer) {
		super();
		this.designer = designer;
		this.thumbnailUrl = generateThumbnailUrl(designer.getImgURL());
		setId(designer.getDesignerId());
	}

	@Override
	protected void setEnglishValue() {
		setName(designer.getEname());
		String content = StringUtil.getStrFromBlob(designer.getDetailContentEN());
		setProfile(content);
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setName(convertTCSC(designer.getCname(), language));
		String content = StringUtil.getStrFromBlob(designer.getDetailContentCH());
		setProfile(convertTCSC(content,language));
	}

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
