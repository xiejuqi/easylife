package org.easylife.web.index;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.config.AppConfig;
import org.easylife.domain.member.Member;
import org.easylife.web.base.BaseController;
import org.easylife.web.shiro.PermissionRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * @Filename IndexController.java
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
 * @Date: 2016年4月14日 上午9:34:06
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
	
	@Autowired
	private PermissionRealm permissionRealm;
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private EhCacheManager shiroEhcacheManager;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String login(ModelMap modelMap,HttpServletRequest request){
		
		logger.info("appConfig={}",appConfig.toString());
		
		Subject currentUser = SecurityUtils.getSubject();  
		
		/*Member member = (Member) request.getSession().getAttribute(SessionAttribute.CURRENT_USER);*/
		
		/*modelMap.addAttribute("member",member);*/
		
		return "index/index";
	}
	
	@RequestMapping(value="/expire",method = RequestMethod.GET)
	public String expire(ModelMap modelMap,HttpServletRequest request){
		
		return "login/expire";
	}
}
