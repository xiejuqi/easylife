package org.easylife.web.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.resultset.ResultSet;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.MemberQueryForm;
import org.easylife.facade.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Filename ResetMemberSessionInfoFilter.java
 * 
 * @Description	会员Session失效之后访问应用(关闭浏览器或者记住我)，重置会员Session信息
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月12日 下午2:36:48
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class ResetMemberSessionInfoFilter implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(ResetMemberSessionInfoFilter.class);
	
	private LoginService loginService;
	
	private CacheManager cacheManager;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpSession session = req.getSession();
		
		Subject currentUser = SecurityUtils.getSubject(); 
		
		PrincipalCollection principalCollection =	currentUser.getPrincipals();
		
		Member sessionMember = (Member) session.getAttribute(SessionAttribute.CURRENT_USER);
		
		if(principalCollection != null){
			
			if(currentUser.isAuthenticated() || currentUser.isRemembered()){
				
				if(sessionMember == null){
					
					MemberQueryForm memberQueryForum = new MemberQueryForm();
					memberQueryForum.setAccount(principalCollection.toString());
					
					ResultSet result = loginService.login(memberQueryForum);
					
					if(result.getStatus()){
						
						Member member = (Member) result.getResultList().get(0);
						
						session.setAttribute(SessionAttribute.CURRENT_USER, member);
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
