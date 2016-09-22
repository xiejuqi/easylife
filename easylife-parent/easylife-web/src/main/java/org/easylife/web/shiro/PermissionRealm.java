package org.easylife.web.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.enumeration.MemberAccountStatusEnum;
import org.easylife.common.exception.CaptchaException;
import org.easylife.common.resultset.ResultSet;
import org.easylife.common.utils.CaptchaUtil;
import org.easylife.common.utils.StringUtils;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.MemberQueryForm;
import org.easylife.domain.member.Permission;
import org.easylife.domain.member.Role;
import org.easylife.facade.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @Filename PermissionRealm.java
 * 
 * @Description	验证用户登录和权限信息，是Shiro的桥梁
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月14日 下午2:24:36
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class PermissionRealm extends AuthorizingRealm {
	
	private final Logger logger = LoggerFactory.getLogger(PermissionRealm.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 授权查询回调函数，每次访问需授权资源时都会执行该方法中的逻辑。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		logger.info("[授权认证,principals={}]",ReflectionToStringBuilder.toString(principals, ToStringStyle.SHORT_PREFIX_STYLE));
		
		/**登录用户帐号*/
		String account = (String)principals.getPrimaryPrincipal(); 
		
		MemberQueryForm memberQueryForum = new MemberQueryForm();
		memberQueryForum.setAccount(account);
		
		ResultSet result = loginService.login(memberQueryForum);
		
		/**系统中存在此用户，获取该用户的角色和权限信息*/
		if(result.getStatus()){
			
			Member member = (Member) result.getResultList().get(0);
			
			if(member.getAccountStatus().equals(MemberAccountStatusEnum.LOCK.getStatusCode())){
				logger.warn("[用户[{}]已经被系统锁定]",member.getAccount());
				throw new LockedAccountException("[该账户已被锁定]");  
			}
			
			SimpleAuthorizationInfo authorizationInfo = getAuthorizationInfo(member);
		    
		    logger.info("[用户授权成功,member={}]",member);
		    
		    return authorizationInfo;
		}
		
		return null;
	}

	/**
	 * 验证当前登录的Subject，该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		EasylifeToken authcToken = (EasylifeToken)token;  
		
		logger.info("[登录认证,authcToken={}]",ReflectionToStringBuilder.toString(authcToken, ToStringStyle.SHORT_PREFIX_STYLE));
		
		if(authcToken != null){
			MemberQueryForm memberQueryForum = new MemberQueryForm();
			memberQueryForum.setAccount(authcToken.getUsername());
			
			String captcha = authcToken.getCaptcha();
			String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(SessionAttribute.KEY_CAPTCHA);

			if(StringUtils.isBlank(captcha)){
				throw new CaptchaException("验证码为空,请输入验证码!");
			}
			
			if(!StringUtils.equalsIgnoreCase(captcha, exitCode)){
				throw new CaptchaException("验证码错误,请输入正确的验证码!");
			}
			
			ResultSet result = loginService.login(memberQueryForum);
			
			if(result.getStatus()){
				
				Member member = (Member) result.getResultList().get(0);
				
				if(member.getAccountStatus().equals(MemberAccountStatusEnum.LOCK.getStatusCode())){
					logger.warn("[用户[{}]已经被系统锁定]",member.getAccount());
					throw new LockedAccountException("[该账户已被锁定]");  
				}
				
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(member.getAccount(),member.getPassword(),member.getName()); 
				this.setSession(SessionAttribute.CURRENT_USER, member);  
				
				logger.info("[用户认证成功,member={}]",member);
				
				return authcInfo;
			}
			
			return null;
		}
		return null;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            logger.info("[Session默认超时时间为{}毫秒]",session.getTimeout());
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  
    
    private SimpleAuthorizationInfo getAuthorizationInfo(Member member) {
		
		/**角色名的集合*/
		Set<String> roles = new HashSet<String>();
		/**权限名的集合*/
		Set<String> permissions = new HashSet<String>();
		/**登录用户的角色集合*/
		Set<Role> userRoles = member.getRoles();
		
		if(userRoles != null && userRoles.size() > 0){
			for(Role role : userRoles){
		    	/**添加角色ID*/
		    	roles.add(role.getCode());
		    	/**获取角色权限*/
		    	Set<Permission> userPermissions = role.getPermissions();
		    	
		    	if(userPermissions != null && userPermissions.size() > 0){
		    		for(Permission permission : userPermissions){
		    			/**添加权限ID*/
			    		permissions.add(permission.getPermission());
			    	}
		    	}
		    }
		}
		
		/**授权用户角色和权限*/
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);
		return authorizationInfo;
	}
    
    /**=================================================在用户修改密码或者更新用户权限之后可以调用下面的方法来清除用户的缓存来更新用户信息================================*/
    
    /**
     * 清除权限缓存
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除用户缓存
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 权限和用户缓存都清除
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 清除所有用户的权限缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }
    
    /**
     * 清除所有用户的用户缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 所有用户的权限和用户缓存都清除
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
