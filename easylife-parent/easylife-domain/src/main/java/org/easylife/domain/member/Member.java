package org.easylife.domain.member;

import java.util.Date;
import java.util.Set;

import org.easylife.common.entity.BaseEntity;
import org.easylife.common.entity.BaseQueryEntity;

/**
 * 
 * @Filename Member.java
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
 * @Date: 2016年4月11日 上午9:25:09
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class Member extends BaseEntity {
	
	private static final long serialVersionUID = 2427190984131693671L;
	
	/**会员名字*/
	private String name;
	
	/**会员帐户*/
	private String account;
	
	/**会员密码*/
	private String password;
	
	/**身份证号*/
	private String pid;

	/**电子邮箱*/
	private String email;
	
	/**手机号码*/
	private String tel;
	
	/**性别*/
	private String sex;
	
	/**是否有效*/
	private String active;
	
	/**账户状态[ACTIVE|LOCK]*/
	private String accountStatus;
	
	/**会员角色集合*/
	private Set<Role> roles;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
