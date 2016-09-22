package org.easylife.common.utils;

import java.util.Arrays;
import java.util.List;

import org.easylife.common.utils.SplitConstants;
import org.easylife.common.utils.StringUtils;

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
public class ListUtil {
	
	/**
	 * 把收到的页面内容转换成核心程序可识别的格式,默认是逗号分割
	 * @param str
	 * @return
	 */
	public static List<String> toList(String str) {
		
		if (StringUtils.isBlank(str)) {
			return null;
		}
		
		// 1. 统一转换中文标点
		str = StringUtils.replace(str, "，", ",");
		
		// 2. 约定只用逗号分割
		String[] array = StringUtils.split(str, SplitConstants.SEPARATOR_CHAR_COMMA);
		List<String> list = Arrays.asList(array);
		return list;
	}
	
	/**
	 * 把收到的页面内容转换成核心程序可识别的格式
	 * @param str
	 * @return
	 */
	public static List<String> toList(String str, String eparatorChar) {
		
		if (StringUtils.isBlank(str)) {
			return null;
		}
		
		// 2. 约定只用逗号分割
		String[] array = StringUtils.split(str, eparatorChar);
		List<String> list = Arrays.asList(array);
		return list;
	}
	
	public static String toStr(List<String> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String _str : list) {
			sb.append(_str).append(SplitConstants.SEPARATOR_CHAR_COMMA);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public static String toStr(String[] list) {
		if (list == null || list.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String _str : list) {
			sb.append(_str).append(SplitConstants.SEPARATOR_CHAR_COMMA);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		if (null == list || list.isEmpty())
			return true;
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		if (null == list || list.isEmpty())
			return false;
		return true;
	}
	
}
