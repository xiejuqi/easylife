package org.easylife.web.base;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.config.AppConfig;
import org.easylife.domain.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @Filename BaseController.java
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
 * @Date: 2016年4月22日 上午10:39:52
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Controller
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EhCacheManager shiroEhcacheManager;
	
	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "redirect:index/index.htm";
	}

	protected void cleanMemberInfo(ModelMap modelMap,HttpServletRequest request) {
		
		Subject currentUser = SecurityUtils.getSubject();

		Member member = (Member) request.getSession().getAttribute(SessionAttribute.CURRENT_USER);
		
		/**先判断会员的Session是否过期，如果Session过期就清除缓存中的会员信息*/
		if(member == null){
			Cache<Object,Object> authenticationCache = shiroEhcacheManager.getCache("authenticationCache");
			Cache<Object,Object> authorizationCache = shiroEhcacheManager.getCache("authorizationCache");
			if(currentUser != null){
				authenticationCache.remove(currentUser.getPrincipals());
				authorizationCache.remove(currentUser.getPrincipals());
			}
		}
	}
}
