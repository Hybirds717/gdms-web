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
	
	private static SqlSessionFactory ssf;	// 定义一个全局的工厂对象
	
	private static Logger logger = Logger.getLogger(GDMSUtil.class);
	
	public static void log(String msg) {
		logger.info(msg);
	}
	
	static {   // 本类被加载的时候，这个代码块只会运行一次
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
		// 接着产生随机9位
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_abcdefghijklmnopqrstuvwxyz";
		int count = length-s.length();
		for ( int i = 0; i < count; i ++ ) {
			int n = (int)(Math.random() * str.length() );  // 随机产生一个下标出来
			s.append(str.charAt(n));	// 取出指定位置的一个字符，拼接进S中
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
		Date d = c.getTime();  // 取得当前日历中的当前日期
		long ms = d.getTime(); // 从1970年到d这个日期的毫秒数
		Timestamp t = new Timestamp(ms);
		return t;
	}
	
	
	/**
	 * 进行MD5加密
	 * @param source
	 * @return 加密后的内容
	 */
	public static String getMD5(byte[] source) throws Exception {
        String s = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        md.update(source);
        byte tmp[] = md.digest();       // MD5 的计算结果是一个 128 位的长整数，
                                        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2];  // 每个字节用 16 进制表示的话，使用两个字符，
                                        // 所以表示成 16 进制需要 32 个字符
        int k = 0;                      // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) {  // 从第一个字节开始，对 MD5 的每一个字节
                                        // 转换成 16 进制字符的转换
            byte byte0 = tmp[i];        // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                                                     // >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf];       // 取字节中低 4 位的数字转换
        }
        s = new String(str);                         // 换后的结果转换为字符串

        return s;
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println(getMD5("123456".getBytes()));
	}
	

}
