package com.oracle.gdms.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public final class GDMSUtil {
	
	private static SqlSessionFactory ssf;	// ����һ��ȫ�ֵĹ�������
	
	private static Logger logger = Logger.getLogger(GDMSUtil.class);
	
	public static void log(String msg) {
		logger.info(msg);
	}
	
	static {   // ���౻���ص�ʱ����������ֻ������һ��
		try {
			Reader reader = Resources.getResourceAsReader("config/mybatis.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession() {
		return ssf.openSession();
	}

	public static String generic(int length) {
		StringBuilder s = new StringBuilder();
		s.append(getCurrentDatetime());
		// ���Ų������9λ
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_abcdefghijklmnopqrstuvwxyz";
		int count = length-s.length();
		for ( int i = 0; i < count; i ++ ) {
			int n = (int)(Math.random() * str.length() );  // �������һ���±����
			s.append(str.charAt(n));	// ȡ��ָ��λ�õ�һ���ַ���ƴ�ӽ�S��
		}
		return s.toString();
	}

	public static String getCurrentDatetime() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int mont = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minu = c.get(Calendar.MINUTE);
		int seco = c.get(Calendar.SECOND);
		int mill = c.get(Calendar.MILLISECOND);
		return "" + year 
				+ (mont<10 ? "0" + mont : mont) 
				+ (date<10 ? "0" + date : date) 
				+ (hour<10 ? "0" + hour : hour)  
				+ (minu<10 ? "0" + minu : minu)   
				+ (seco<10 ? "0" + seco : seco)   
				+ (mill<10 ? "00" + mill : (mill<100? "0" + mill : mill) );
	}

	public static Timestamp now() {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();  // ȡ�õ�ǰ�����еĵ�ǰ����
		long ms = d.getTime(); // ��1970�굽d������ڵĺ�����
		Timestamp t = new Timestamp(ms);
		return t;
	}
	
	
	/**
	 * ����MD5����
	 * @param source
	 * @return ���ܺ������
	 */
	public static String getMD5(byte[] source) throws Exception {
        String s = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        md.update(source);
        byte tmp[] = md.digest();       // MD5 �ļ�������һ�� 128 λ�ĳ�������
                                        // ���ֽڱ�ʾ���� 16 ���ֽ�
        char str[] = new char[16 * 2];  // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
                                        // ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
        int k = 0;                      // ��ʾת������ж�Ӧ���ַ�λ��
        for (int i = 0; i < 16; i++) {  // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
                                        // ת���� 16 �����ַ���ת��
            byte byte0 = tmp[i];        // ȡ�� i ���ֽ�
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��,
                                                     // >>> Ϊ�߼����ƣ�������λһ������
            str[k++] = hexDigits[byte0 & 0xf];       // ȡ�ֽ��е� 4 λ������ת��
        }
        s = new String(str);                         // ����Ľ��ת��Ϊ�ַ���

        return s;
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println(getMD5("123456".getBytes()));
	}
	

}
