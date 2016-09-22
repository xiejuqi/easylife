package org.easylife.domain.member;

import java.util.List;

import org.easylife.common.entity.BaseEntity;

/**
 * 
 * @Filename Tree.java
 * 
 * @Description 会员权限列表所对应的树形结构(包含父子层级关系)
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月29日 下午3:40:20
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class Tree extends BaseEntity {

	private static final long serialVersionUID = -1135276042253354933L;

	/** 主键 */
	private Integer permissionId;

	/** 权限名称 */
	private String name;

	/** 父权限ID */
	private Integer parentId;

	/** 权限描述 */
	private String description;

	/** 权限链接地址 */
	private String href;

	/** 权限是否显示 */
	private String visible;

	/** 权限类型[菜单|按钮|页面] */
	private String type;

	/** 权限内容字符串 */
	private String permission;

	/** 是否打开 */
	private String state = "closed";

	/** 是否选中 */
	private boolean checked = false;

	/** 子菜单 */
	private List<Tree> children;

	/** 图标 */
	private String icon;

	/** css类名 */
	private String cssClass;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
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

	public Tree() {

	}

	public Tree(int id, int parentId, String text, String icon, String cssClass, String permission, String href) {
		this.permissionId = id;
		this.parentId = parentId;
		this.name = text;
		this.icon = icon;
		this.cssClass = cssClass;
		this.permission = permission;
		this.href = href;
	}
}
