package org.easylife.common.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 
 * @Filename CaptchaException.java
 * 
 * @Description 验证码异常,会员登录验证码错误抛出该异常,会被Shiro捕捉到。
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月28日 上午9:56:40
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -1117210602441149109L;

	public CaptchaException() {
		super();
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(Throwable cause) {
		super(cause);
	}
}
