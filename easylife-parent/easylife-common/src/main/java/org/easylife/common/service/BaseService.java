package org.easylife.common.service;

import org.easylife.common.entity.BaseEntity;

/**
 * 
 * @Filename BaseService.java
 * 
 * @Description	业务层抽象出的接口
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月13日 下午4:25:41
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public interface BaseService {

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public int updateEntity(BaseEntity bt);

	
}