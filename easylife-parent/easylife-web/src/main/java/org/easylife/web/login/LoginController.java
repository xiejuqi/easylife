package org.easylife.web.login;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.exception.CaptchaException;
import org.easylife.common.resultset.ResultSet;
import org.easylife.common.utils.Base64Util;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.MemberQueryForm;
import org.easylife.domain.member.Tree;
import org.easylife.facade.member.MemberService;
import org.easylife.web.base.BaseController;
import org.easylife.web.shiro.EasylifeToken;
import org.easylife.web.shiro.PermissionRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 
 * @Filename LoginController.java
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
 * @Date: 2016年4月7日 上午11:17:08
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
	
	@Autowired
	private PermissionRealm permissionRealm;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 返回登录页面给用户,如果已经登录直接跳转至首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request){
		
		 Subject currentUser = SecurityUtils.getSubject();  
        
		 //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
            return "redirect:/index/index.htm";
        }
        
		return "login/login";
	}
	
	/**
	 * 校验用户登录是否OK
	 * @param modelMap
	 * @param memberQueryForum
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(ModelMap modelMap,MemberQueryForm memberQueryForum,HttpServletRequest request){
		
		String account =  memberQueryForum.getAccount();
		String password = memberQueryForum.getPassword();
		
		
		EasylifeToken token =new EasylifeToken(account, new String(Base64Util.getFromBase64(password)),memberQueryForum.getCaptcha()); 
		token.setRememberMe(memberQueryForum.isRememberMe());  
		
		logger.info("[登录用户为:{}]",ReflectionToStringBuilder.toString(token, ToStringStyle.SHORT_PREFIX_STYLE));
		
		//获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);  
        }catch(UnknownAccountException uae){  
            logger.info("[用户{}进行登录验证,验证未通过,未知账户,异常信息:{}]",uae.getMessage());
            modelMap.addAttribute("message_login", "未知账户");  
            return "login/login";
        }catch(IncorrectCredentialsException ice){  
            logger.info("[用户{}进行登录验证,验证未通过,错误的凭证,异常信息:{}]",ice.getMessage());
            modelMap.addAttribute("message_login", "密码不正确");  
            return "login/login";
        }catch(LockedAccountException lae){  
            logger.info("[用户{}进行登录验证,验证未通过,账户已锁定,异常信息:{}]",lae.getMessage());
            modelMap.addAttribute("message_login", "账户已锁定");  
            return "login/login";
        }catch(ExcessiveAttemptsException eae){  
            logger.info("[用户{}进行登录验证,验证未通过,错误次数过多,账户被锁定,异常信息:{}]",eae.getMessage());
            modelMap.addAttribute("message_login", "用户名或密码错误次数过多,账户被锁定");  
            return "login/login";
        }catch(ExpiredCredentialsException ece){
        	logger.info("[用户{}进行登录验证,凭证过期,异常信息:{}]",ece.getMessage());
        	modelMap.addAttribute("message_login", "会员凭证过期"); 
        	return "login/login";
        }catch(CaptchaException ce){
        	logger.info("[用户{}输入验证码错误,请重试！异常信息:{}]",ce.getMessage());
        	modelMap.addAttribute("message_login", "验证码错误"); 
        	return "login/login";
        }catch(AuthenticationException ae){  
            logger.info("用户{}进行登录验证,验证未通过,堆栈轨迹如下:{}",ae.getMessage());
            ae.printStackTrace();  
            modelMap.addAttribute("message_login", "用户名或密码不正确");  
            return "login/login";
        }  
        
        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
        	
        	currentUser.login(token);
        	logger.info("[用户[{}]登录成功！]",account);
            logger.info("[{}]",request.getContextPath());
            return "redirect:/index/index.htm";
        }else{  
            token.clear();  
        }  
        
		return "login/login";
	}
	
	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response){
		Subject currentUser = SecurityUtils.getSubject();
		permissionRealm.clearCache(currentUser.getPrincipals());
		currentUser.logout();
		
		return "redirect:/login/login.htm";
	}
	
	
	/**
	 * ajax返回登录用户权限信息，加载权限树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getMemberInfo",method = RequestMethod.GET)
	@ResponseBody
	public List<Tree> getMemberInfo(HttpServletRequest request,HttpServletResponse response){
		
		Subject currentUser = SecurityUtils.getSubject(); 
		Member member = (Member) currentUser.getSession().getAttribute(SessionAttribute.CURRENT_USER);
		
		ResultSet rs = memberService.getMemberPermissions(member);
		
		List<Tree> treeList = rs.getResultList();
		
		return treeList;
	}
}
