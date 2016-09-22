package org.easylife.common.enumeration;
/**
 * 
 * @Filename MemberAccountStatusEnum.java
 * 
 * @Description	会员帐户状态枚举
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月18日 下午2:57:48
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public enum MemberAccountStatusEnum {
	
	/**激活状态，可登录*/
	ACTIVE("ACTIVE","激活"),
	
	/**锁定状态，不可登录*/
	LOCK("LOCK","锁定");
	
	private String statusCode;
	private String message;
	
	private MemberAccountStatusEnum(String statusCode,String message){
		this.statusCode=statusCode;
		this.message=message;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.statusCode);
	}
	
	public String getStatusCode() {
		return statusCode;
	}

}
