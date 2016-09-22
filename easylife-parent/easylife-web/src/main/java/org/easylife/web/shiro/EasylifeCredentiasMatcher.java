package org.easylife.web.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.config.AppConfig;
import org.easylife.common.encoder.Md5PwdEncoder;
import org.easylife.common.enumeration.MemberAccountStatusEnum;
import org.easylife.common.exception.CaptchaException;
import org.easylife.common.utils.StringUtils;
import org.easylife.domain.login.LoginStatusLock;
import org.easylife.facade.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @Filename EasylifeCredentiasMatcher.java
 * 
 * @Description 易生活用户密码登录验证器，输错超过5次数就被锁定100分钟
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月18日 上午10:15:08
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class EasylifeCredentiasMatcher extends SimpleCredentialsMatcher {

	private final Logger logger = LoggerFactory.getLogger(EasylifeCredentiasMatcher.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AppConfig appConfig;
	
	/**记录会员输入密码次数，集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发*/
	private Cache<String, LoginStatusLock> passwordRetryCache;
	private Cache<String, Object> authenticationCache;
	
	public EasylifeCredentiasMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		authenticationCache = cacheManager.getCache("authenticationCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		EasylifeToken usernamePasswordToken = (EasylifeToken)token;
		
		String account = usernamePasswordToken.getUsername();
		
		String password = String.valueOf(usernamePasswordToken.getPassword());
		
		String userCaptcha = usernamePasswordToken.getCaptcha();
		
		String captcha = (String) SecurityUtils.getSubject().getSession().getAttribute(SessionAttribute.KEY_CAPTCHA);  
		
		/**验证验证码是否正确*/
		if(StringUtils.isBlank(captcha) || StringUtils.isBlank(userCaptcha)){
			throw new CaptchaException("验证码为空,请输入验证码！");
		}
		
		if(!StringUtils.equalsIgnoreCase(captcha, userCaptcha)){
			throw new CaptchaException("验证码错误！");
		}
		
		/**获得加密的密码*/
		Object tokenCredentials = Md5PwdEncoder.encodePassword(password, account);
		
		/**使用自定义的Shiro*/
		Object accountCredentials = getCredentials(info);
		
		/**验证密码输入错误是否超过5次 retry count + 1*/
		LoginStatusLock statusLock = passwordRetryCache.get(account);
		
		if (null == statusLock) {
			AtomicInteger retryCount = new AtomicInteger(1);
			
			LoginStatusLock lock = new LoginStatusLock();
			lock.setLoginTimes(retryCount);
			
			passwordRetryCache.put(account, lock);
		}else{
			
			if(statusLock.getStatusEnum() == MemberAccountStatusEnum.LOCK){
				logger.warn("[用户[{}]已经被系统锁定]",account);
				throw new LockedAccountException("[该账户已被锁定]");  
			}
			
			if (	statusLock.getLoginTimes().incrementAndGet() >= appConfig.getMemberMaxloginTime()) {
				
				logger.warn("用户：{}密码输入错误次数过多！");
				
				/**锁定会员*/
				
				/**1.缓存锁定，到指定时间会自动激活用户*/
				statusLock.setStatusEnum(MemberAccountStatusEnum.LOCK);
				
				/**2.数据库锁定,需要人工激活用户*/
				/*Member member = new Member();
				member.setAccount(account);
				member.setAccountStatus(MemberAccountStatusEnum.LOCK.getStatusCode());
				ResultSet rs = memberService.updateMember(member);
				
				if(rs.getStatus()){
					*//**清除会员缓存*//*
					authenticationCache.remove(account);
				}*/
				
				throw new ExcessiveAttemptsException(
						"用户: " + account + "密码输入错误次数过多！");
			}
		}
		
		/**验证加密后的密码是否是正确的*/
		Boolean	matches = equals(tokenCredentials, accountCredentials);
		
		if (matches) {
			passwordRetryCache.remove(account);
		}
		
		return matches;
	}
}
