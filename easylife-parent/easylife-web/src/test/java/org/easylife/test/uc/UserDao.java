package org.easylife.test.uc;

import org.easylife.test.domain.Student;
/**
 * 
 * @Filename UserDao.java
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
 * @Date: 2016年5月31日 上午9:50:43
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class UserDao extends BaseDao<Student>{
	
	public static void main(String[] args) {
		UserDao u = new UserDao();
		System.out.println(u.getEntityClass());
	}
}
