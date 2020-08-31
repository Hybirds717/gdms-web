package com.oracle.gdms.web.listener;

import java.net.URL;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class AppListener implements ServletContextListener {
	
	private static ResourceBundle rb;	// ��Դ�󶨵Ķ���
	
	public static String getString(String key) {
		return rb.getString(key);
	}
  
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

	
    public void contextInitialized(ServletContextEvent arg0)  {
    	
    	// ��ȡһ��application.properties
    	rb = ResourceBundle.getBundle("config/application"); // ��application.properties��rb�����������
    	
    	URL url = AppListener.class.getClassLoader().getResource(rb.getString("log4jpath"));
		// �ҵ�log4j���ģ����߿��
    	PropertyConfigurator.configure(url);
    	
    	// �����Ӧ�ó�����Ҫ��һЩ������ȫ�Ž�ȫ�ֻ�����http://localhost:8080/gdms-web/
    	String href = "http://nxofg102679.java.cdnjsp.org/" + rb.getString("context") + "/";
    	
    	arg0.getServletContext().setAttribute("href", href);  // ��ȫ���ڴ滷���а�һ������
    }
	
}
