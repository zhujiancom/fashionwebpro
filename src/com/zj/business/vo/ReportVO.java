package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Report;
import com.zj.common.utils.StringUtil;

public class ReportVO  extends AbstractVO{
	private Report report;
	private long id;
	private String thumbnail;
	private String title;
	private String content;
	private String preview;
	
	public ReportVO(Report report){
		this.report = report;
		this.thumbnail = generateThumbnailUrl(report.getReportimg());
		setId(report.getReportid());
	}
	
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setTitle(convertTCSC(report.getReportCname(),language));
		String detail = StringUtil.getStrFromBlob(report.getDetailContentCH());
		setContent(convertTCSC(detail, language));
		setPreview(convertTCSC(report.getPreviewCH(),language));
	}

	@Override
	protected void setEnglishValue() {
		setTitle(report.getReportEname());
		String detail = StringUtil.getStrFromBlob(report.getDetailContentEN());
		setContent(detail);
		setPreview(report.getPreviewEN());
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	
}
