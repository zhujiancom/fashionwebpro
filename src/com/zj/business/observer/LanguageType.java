package com.zj.business.observer;

public enum LanguageType {
	 EN_US("en_US"),ZH_CN("zh_CN"),ZH_TW("zh_TW");
	 private String name;
	    
	    private LanguageType(String name){
	        this.name = name;
	    }
	    
	    public String getName(){
	        return name;
	    }
	    
	    public static LanguageType toLanguageType(String name){
	        try{
	            return valueOf(name);
	        }catch(Exception e){
	            System.out.println("convert to language type error!");
	            throw new RuntimeException(e);
	        }
	    }
	    
	    public String toString(){
	        return name;
	    }
}
