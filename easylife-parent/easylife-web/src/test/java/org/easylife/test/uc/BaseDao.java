package org.easylife.test.uc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @Filename BaseDao.java
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
 * @Date: 2016年5月31日 上午9:45:46
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class BaseDao<T> {

	private Class entityClass;

	public BaseDao() {
		/** 通过反射方式获取子类Dao对应的泛型实体类 */
		Type genType = getClass().getGenericSuperclass();
		if(genType instanceof ParameterizedType){
			System.out.println("genType instanceof ParameterizedType");
		}else{
			System.out.println("genType not instanceof ParameterizedType");
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
}
