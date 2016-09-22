package org.easylife.web.member;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.easylife.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @Filename MemberRegisterController.java
 * 
 * @Description	会员注册控制器
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月20日 下午5:29:56
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Controller
@RequestMapping(value = "member")
public class MemberRegisterController extends BaseController {
	
	/**
	 * 查询出登录用户的权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getLoginUserPermissions(HttpServletRequest request){
		logger.info("[in MemberRegisterController getLoginUserPermissions... ]");
		return "member/register";
	}
}
