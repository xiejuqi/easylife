package org.easylife.web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @Filename EasylifeToken.java
 * 
 * @Description 扩展ShiroUsernamePasswordToken,增加验证码属性
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月28日 上午10:03:20
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class EasylifeToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = 1L;
	
	/**验证码属性*/
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public EasylifeToken() {
		super();
	}

	public EasylifeToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
	
	public  EasylifeToken(final String username, final String password, final String captcha) {
		super(username,password);
		this.captcha = captcha;
    }
}
