package org.easylife.facade.member;

import org.easylife.common.resultset.ResultSet;
import org.easylife.domain.member.Member;

/**
 * 
 * @Filename MemberService.java
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
 * @Date: 2016年4月18日 下午3:18:27
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public interface MemberService {
	
	/**
	 * 根据主键更新Member对象，主键不能为空
	 * @param member
	 * @return
	 */
	public ResultSet updateMember(Member member);
	
	/**
	 * @param member
	 * @return
	 */
	public ResultSet getMemberPermissions(Member member);
}
