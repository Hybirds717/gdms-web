package com.oracle.gdms.web.action;

import java.util.ResourceBundle;


public class Factory {

		private Factory() {}
	
	private static Factory fac = new Factory();
	
	public static Factory getInstance() {
		
		return fac;
	}
	
public Object getDao(String key) {      //getDao��ΪgetImpl
		
		ResourceBundle b = ResourceBundle.getBundle("config/application");
		
		String clsname = b.getString(key);   //��ȡ�û���Ҫ����·������
		
		//ͨ�������ȡһ�����󣬽�������
		Object o = null;
		try {
			o = Class.forName(clsname).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return o;
	}


}
