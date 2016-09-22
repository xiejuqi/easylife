package org.easylife.common.utils;

import java.io.UnsupportedEncodingException;

import org.easylife.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.*;  

/**
 * 
 * @Filename Base64Util.java
 * 
 * @Description	加密,解密页面传输过来的会员密码
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月28日 上午10:28:05
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class Base64Util{
	
	private static  Logger logger = LoggerFactory.getLogger(Base64Util.class);
	
	// 加密  
    @SuppressWarnings("restriction")
	public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
        	logger.error("[加密发生错误,加密字符串:{},Exception:{}]",str,e);
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    @SuppressWarnings("restriction")
	public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
            	logger.error("[解密发生错误,加密字符串:{},Exception:{}]",s,e);
            }  
        }  
        return result;  
    }  
}
