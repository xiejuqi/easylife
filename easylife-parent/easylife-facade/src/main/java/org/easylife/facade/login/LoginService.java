package org.easylife.facade.login;

import org.easylife.common.resultset.ResultSet;
import org.easylife.domain.member.MemberQueryForm;

/**
 * 
 * @Filename LoginService.java
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
 * @Date: 2016年4月8日 下午12:24:41
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */

public interface LoginService {
	/**
	 * 验证用户登录
	 * @param loginForum
	 * @return
	 */
	public ResultSet login(MemberQueryForm memberQueryForum);
}
