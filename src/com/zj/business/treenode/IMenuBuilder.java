package com.zj.business.treenode;


public interface IMenuBuilder {
	public static final String BRAND = "BRAND";
	public static final String INTERVIEWS = "INTERVIEWS";
	public static final String VIDEOS = "VIDEOS";
	public static final String AUDIOS = "AUDIOS";
	public static final String PRESSROPORTS = "PRESSROPORTS";
	public static final String COLLECTIONS = "COLLECTIONS";
	public static final String LOOKBOOK_IMAGES = "LOOKBOOK IMAGES";
	public static final String EDITORIAL_IMAGES = "EDITORIAL IMAGES";
	public static final String RUNWAY_SHOWS = "RUNWAY SHOWS";
	public static final String UNKNOW="UNKNOW";
	Menu createDesignerMenu();
	Menu createBrandMenu();
	Menu createInterviewMenu();
	Menu createCollectionMenu();
}