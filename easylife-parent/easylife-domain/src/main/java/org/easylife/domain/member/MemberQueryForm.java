package org.easylife.domain.member;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.easylife.common.annotation.Percent;
import org.easylife.common.annotation.SingleQuotes;
import org.easylife.common.entity.BaseQueryEntity;

/**
 * 
 * @Filename MemberQueryForm.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月13日 下午2:56:33
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class MemberQueryForm extends BaseQueryEntity {

	private static final long serialVersionUID = -6354189342324248617L;
	
	private String name;
	
	@SingleQuotes
	private String account;
	
	@Percent
	private String password;
	
	private boolean rememberMe = false;
	
	private String captcha;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
