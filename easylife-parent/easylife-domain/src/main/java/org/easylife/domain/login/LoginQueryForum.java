package org.easylife.domain.login;

import org.easylife.common.entity.BaseQueryEntity;

/**
 * 
 * @Filename LoginQueryForum.java
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
 * @Date: 2016年4月8日 下午12:15:44
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class LoginQueryForum extends BaseQueryEntity {

	private static final long serialVersionUID = -219555246568274346L;
	
	private String userName;
	
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public static void main(String[] args) {
		System.out.println(String.format("\'%s\'", "王力"));
	}
}
