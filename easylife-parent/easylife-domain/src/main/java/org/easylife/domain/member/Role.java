package org.easylife.domain.member;

import java.util.Set;

import org.easylife.common.entity.BaseEntity;

/**
 * 
 * @Filename Role.java
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
 * @Date: 2016年4月14日 下午2:47:50
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = -547559169444240206L;
	
	/**主键*/
	private Integer roleId;
	
	/**角色编码*/
	private String code;
	
	/**角色名称*/
	private String name;
	
	/**角色描述*/
	private String description;
	
	/**会员权限集合*/
	private Set<Permission> permissions;

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
