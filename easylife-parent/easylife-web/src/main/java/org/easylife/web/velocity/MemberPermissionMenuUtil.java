package org.easylife.web.velocity;

import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.config.AppConfig;
import org.easylife.common.resultset.ResultSet;
import org.easylife.common.utils.ListUtil;
import org.easylife.common.utils.StringUtils;
import org.easylife.domain.member.Member;
import org.easylife.domain.member.Tree;
import org.easylife.facade.member.MemberService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @Filename MemberPermissionMenuUtil.java
 * 
 * @Description 根据会员信息构建会员的权限菜单(不传会员的话就获取当前登录会员的权限信息)
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月16日 上午10:05:05
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class MemberPermissionMenuUtil implements ApplicationContextAware {
	
	private static ApplicationContext ac;
	
	@SuppressWarnings("unchecked")
	public static String getPermissionMenu(Member member) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if(member == null){
			
			member = (Member) currentUser.getSession().getAttribute(SessionAttribute.CURRENT_USER);
			
			if(member == null){
				return "";
			}
		}
		
		/**从Session中获取当前用户权限，如果不存在则查询数据库*/
		String currentUserPermission = (String) currentUser.getSession().getAttribute(SessionAttribute.CURRENT_USER_PERMISSION);
		if(StringUtils.isNotBlank(currentUserPermission)){
			return currentUserPermission;
		}
		
		
		MemberService memberService = (MemberService) ac.getBean("memberService");
		AppConfig appConfig = (AppConfig) ac.getBean("appConfig");
		
		
		StringBuffer permissionElement = new StringBuffer("");
		
		if(member != null){
			
			/**获取该用户的所有权限列表*/
			ResultSet rs = memberService.getMemberPermissions(member);
			
			List<Tree> treeList = rs.getResultList();
			
			if(ListUtil.isNotEmpty(treeList)){
				for(Tree tree : treeList){
					
					List<Tree> cMenu = tree.getChildren();
					
					permissionElement.append("<li class=''><a href='javascript:;'> <i class="+tree.getIcon()+">"+"</i> <span class='title'>"+tree.getName());
					
					/**该菜单下还有子菜单的话就显示向下箭头展开的图样*/
					if(ListUtil.isNotEmpty(cMenu)){
						permissionElement.append("</span><span class='arrow'></span> </a>");
					}else{
						permissionElement.append("</span><span class=''></span> </a>");
					}
					
					createSubMenu(permissionElement,tree,appConfig);
					
					permissionElement.append("</li>");
					
				}
			}
		}
		
		currentUser.getSession().setAttribute(SessionAttribute.CURRENT_USER_PERMISSION, permissionElement.toString());
		return permissionElement.toString();
	}

	private static void createSubMenu(StringBuffer permissionElement,Tree tree,AppConfig appConfig) {
		
		List<Tree> childMenu = tree.getChildren();
		
		if(ListUtil.isNotEmpty(childMenu)){
			permissionElement.append("<ul class='sub-menu'>");
			for(Tree childTree : childMenu){
				
				permissionElement.append("<li>");
				
				/**节点链接不为空*/
				if(StringUtils.isNotBlank(childTree.getHref())){
					permissionElement.append("<a href='"+"/"+appConfig.getAppName()+childTree.getHref()+"'>");
				}else{
					permissionElement.append("<a href='javascript:;'>");
				}
				
				permissionElement.append("<i class="+childTree.getIcon()+">"+"</i> <span class='title'>"+childTree.getName());
				
				
				List<Tree> cMenu = childTree.getChildren();
				
				if(ListUtil.isNotEmpty(cMenu)){
					permissionElement.append("</span><span class='arrow'></span> </a>");
					createSubMenu(permissionElement,childTree,appConfig);
				}else{
					permissionElement.append("</span><span class=''></span> </a>");
					permissionElement.append("</li>");
				}
			}
			permissionElement.append("</ul>");
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MemberPermissionMenuUtil.ac = applicationContext;
	}

}
