package org.easylife.common.service;
import org.easylife.common.dao.BaseMapper;
import org.easylife.common.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @Filename BaseServiceImpl.java
 * 
 * @Description	业务层抽象出的接口实现类，可以通过继承复用方法
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
public abstract class BaseServiceImpl {
	/*implements BaseService {

	public abstract BaseMapper getMapper();

	public int updateEntity(BaseEntity t) {
		return this.getMapper().updateEntity(t);
	}*/
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
