package org.easylife.domain.member;

import java.util.Date;
import java.util.Set;

import org.easylife.common.entity.BaseEntity;

/**
 * 
 * @Filename Permission.java
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
 * @Date: 2016年4月14日 下午2:48:01
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = 175918380902683135L;
	
	/**主键*/
	private Integer permissionId;
	
	/**权限名称*/
	private String name;
	
	/**父权限ID*/
	private Integer parentId;
	
	/**权限描述*/
	private String description;
	
	/**权限链接地址*/
	private String href;
	
	/**权限是否显示*/
	private String visible;
	
	/**权限类型[菜单|按钮|页面]*/
	private String type;
	
	/**权限内容字符串*/
	private String permission;
	
	private String icon;
	
	private String cssClass;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
}
