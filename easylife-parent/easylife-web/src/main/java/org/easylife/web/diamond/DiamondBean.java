package org.easylife.web.diamond;

import java.io.Serializable;

/**
 * 
 * @Filename DiamondBean.java
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
 * @Date: 2016年6月3日 下午4:09:06
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class DiamondBean implements Serializable {
	private String group;
	private String dataId;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
}
