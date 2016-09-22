package org.easylife.web.shiro;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.mgt.SecurityManager;

/**
 * 
 * @Filename EasylifeFilterFactoryBean.java
 * 
 * @Description	扩展ShiroFilterFactoryBean,使用新建的EasylifeFilterFactoryBean。 
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月19日 下午3:35:48
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class EasylifeFilterFactoryBean extends ShiroFilterFactoryBean {
	
	@Override
	public Class getObjectType() {
		return EasylifeSpringShiroFilter.class;
	}

	@Override
	protected AbstractShiroFilter createInstance() throws Exception {

		SecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			String msg = "SecurityManager property must be set.";
			throw new BeanInitializationException(msg);
		}

		if (!(securityManager instanceof WebSecurityManager)) {
			String msg = "The security manager does not implement the WebSecurityManager interface.";
			throw new BeanInitializationException(msg);
		}

		FilterChainManager manager = createFilterChainManager();

		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		chainResolver.setFilterChainManager(manager);

		return new EasylifeSpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
	}

	private static final class EasylifeSpringShiroFilter extends AbstractShiroFilter {

		protected EasylifeSpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
			super();
			if (webSecurityManager == null) {
				throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
			}
			setSecurityManager(webSecurityManager);
			if (resolver != null) {
				setFilterChainResolver(resolver);
			}
		}

		@Override
		protected ServletResponse wrapServletResponse(HttpServletResponse orig, ShiroHttpServletRequest request) {
			return new EasylifeHttpServletResponse(orig, getServletContext(), request);
		}
	}
}
