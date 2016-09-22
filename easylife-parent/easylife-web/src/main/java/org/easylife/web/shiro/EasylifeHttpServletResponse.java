package org.easylife.web.shiro;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;

/**
 * 
 * @Filename EasylifeHttpServletResponse.java
 * 
 * @Description	原生Shiro重写URL时会加上JSESSIONID,继承之后去掉拼接的JSESSIONID。
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月19日 下午2:16:12
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class EasylifeHttpServletResponse extends ShiroHttpServletResponse {

	public EasylifeHttpServletResponse(HttpServletResponse wrapped, ServletContext context,
			ShiroHttpServletRequest request) {
		super(wrapped, context, request);
	}

	@Override
	protected String toEncoded(String url, String sessionId) {
		if ((url == null) || (sessionId == null))
			return (url);

		String path = url;
		String query = "";
		String anchor = "";
		int question = url.indexOf('?');
		if (question >= 0) {
			path = url.substring(0, question);
			query = url.substring(question);
		}
		int pound = path.indexOf('#');
		if (pound >= 0) {
			anchor = path.substring(pound);
			path = path.substring(0, pound);
		}
		StringBuilder sb = new StringBuilder(path);
		
		/**覆盖父类中的重写URL方法，去掉URL中的JSESSIONID*/
		/*if (sb.length() > 0) { // session id param can't be first.
			sb.append(";");
			sb.append(DEFAULT_SESSION_ID_PARAMETER_NAME);
			sb.append("=");
			sb.append(sessionId);
		}*/
		
		sb.append(anchor);
		sb.append(query);
		return (sb.toString());
	}

}
