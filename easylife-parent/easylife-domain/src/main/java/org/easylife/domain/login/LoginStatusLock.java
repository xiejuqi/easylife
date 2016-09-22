package org.easylife.domain.login;

import java.util.concurrent.atomic.AtomicInteger;

import org.easylife.common.entity.BaseEntity;
import org.easylife.common.enumeration.MemberAccountStatusEnum;

/**
 * 
 * @Filename LoginStatusLock.java
 * 
 * @Description	用于保存会员登录信息,超过指定登录次数将被锁定(不操作数据库，控制缓存中的数据)。
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月19日 下午12:07:02
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class LoginStatusLock extends BaseEntity {
	
	private static final long serialVersionUID = 4418519364861780743L;

	/**登录次数*/
	private AtomicInteger loginTimes;
	
	/**账户状态,默认激活*/
	private MemberAccountStatusEnum statusEnum = MemberAccountStatusEnum.ACTIVE;
	
	public AtomicInteger getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(AtomicInteger loginTimes) {
		this.loginTimes = loginTimes;
	}
	public MemberAccountStatusEnum getStatusEnum() {
		return statusEnum;
	}
	public void setStatusEnum(MemberAccountStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
}
