package org.easylife.common.config;

import org.springframework.beans.factory.annotation.Value;
/**
 * 
 * @Filename AppConfig.java
 * 
 * @Description	application.properties文件中的系统配置可以在这里得到
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月21日 下午5:16:32
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class AppConfig {
	
	/**会员尝试登录次数,超出即被锁定*/
	@Value("${member.maxlogin.time}")
	private Integer memberMaxloginTime;
	
	@Value("${app.name}")
	private String appName;
	

	public  Integer getMemberMaxloginTime() {
		return memberMaxloginTime;
	}

	public String getAppName() {
		return appName;
	}
}
