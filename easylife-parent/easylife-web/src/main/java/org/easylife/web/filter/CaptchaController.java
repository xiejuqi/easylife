package org.easylife.web.filter;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.easylife.common.attr.SessionAttribute;
import org.easylife.common.utils.CaptchaUtil;
import org.easylife.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @Filename CaptchaController.java
 * 
 * @Description 创建验证码的Servlet,用户登录时输入到页面展示。
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月28日 上午11:39:05
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Controller
@RequestMapping(value = "/captchaCode", method = RequestMethod.GET)
public class CaptchaController extends BaseController {

	@RequestMapping(value = "/create.htm", method = RequestMethod.GET)
	public void createCaptchaCode(HttpServletRequest request, HttpServletResponse response) {

		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		// 不缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		try {

			HttpSession session = request.getSession();

			CaptchaUtil tool = new CaptchaUtil();
			StringBuffer code = new StringBuffer();
			BufferedImage image = tool.genRandomCodeImage(code);
			session.removeAttribute(SessionAttribute.KEY_CAPTCHA);
			session.setAttribute(SessionAttribute.KEY_CAPTCHA, code.toString());

			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", response.getOutputStream());

		} catch (Exception e) {
			logger.error("[生成验证码发生异常,异常信息:{}]",e);
		}
	}
}
