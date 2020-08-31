package com.oracle.gdms.web.action;

import java.util.ResourceBundle;


public class Factory {

		private Factory() {}
	
	private static Factory fac = new Factory();
	
	public static Factory getInstance() {
		
		return fac;
	}
	
public Object getDao(String key) {      //getDao改为getImpl
		
		ResourceBundle b = ResourceBundle.getBundle("config/application");
		
		String clsname = b.getString(key);   //获取用户需要的类路径名称
		
		//通过反射获取一个对象，将他返回
		Object o = null;
		try {
			o = Class.forName(clsname).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}


}
