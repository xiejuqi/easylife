package org.easylife.common.utils;

/**
 * 
 * @Filename Md5PwdEncoder.java
 * 
 * @Description	使用MD5加密,根据用户的密码和用户名得到密文密码
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月18日 上午10:30:29
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class SplitConstants {
	
	public final static String SEPARATOR_CHAR_COMMA = ",";
	
	public final static String SEPARATOR_CHAR_SLASH = "/";
	
	public final static String SEPARATOR_CHAR_HYPHEN = "-";
	
	public final static String SEPARATOR_CHAR_PERIOD = ".";
	
	public final static String SEPARATOR_CHAR_UNDERLINE = "_";
	
	public final static String SEPARATOR_CHAR_ASTERISK = "*";
	
	public final static String SEPARATOR_CHAR_COLON = ":";
	
	/**
	 * win下的换行符
	 */
	public final static String SEPARATOR_NEWLINE_WIN = "\r\n";
	
	/**
	 * linux,UNIX mac下的换行符 MAC os x之前的系统我们接触不到，他的换行符是\r，不考虑了
	 * 参考：https://en.wikipedia.org/wiki/Newline
	 */
	public final static String SEPARATOR_NEWLINE_LINUX = "\n";
	
	/**
	 * 获取当前系统换行符
	 * @return
	 */
	public static String getNewLineSymbol() {
		return System.getProperty("line.separator", "\n");
	}
	
}
