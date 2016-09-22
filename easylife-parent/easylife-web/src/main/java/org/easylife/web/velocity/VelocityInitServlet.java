package org.easylife.web.velocity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.VelocityViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Filename VelocityInitServlet.java
 * 	
 * @Description	读取Velocity配置信息，初始化VelocityEngine
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年4月14日 上午11:06:41
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class VelocityInitServlet extends VelocityViewServlet {

	private static final long serialVersionUID = -8480640796357946710L;

	private Logger logger = LoggerFactory.getLogger(VelocityInitServlet.class);

	private VelocityEngine velocitypEngine;

	@Override
	public void init() throws ServletException {
		logger.info("[初始化VelocityInitServlet]");
		
		/**velocity引擎对象*/
		velocitypEngine = new VelocityEngine();
		
		/**设置vm模板的装载路径*/
		Properties prop = new Properties();
		
		try {
			
			prop = this.loadConfiguration();
			logger.info("[Velocity属性文件读取成功]");
			
		} catch (Exception e) {
			logger.error("[Velocity属性文件读取失败,错误信息为:{}]",e);
		} 
		
		try {
			/**初始化设置，下面用到getTemplate("*.vm")输出时;一定要调用velocitypEngine对象去做,即velocitypEngine.getTemplate("*.vm")*/
			velocitypEngine.init(prop);
			logger.info("[Velocity引擎初始化]");
		} catch (Exception e1) {
			logger.error("[Velocity引擎初始化失败,错误信息为:{}]",e1);
		}
	}

	protected Properties loadConfiguration() throws IOException, FileNotFoundException {
		
		/**得到属性文件，找到后要加载*/
		String propsFile = this.getInitParameter("org.apache.velocity.properties");
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propsFile);
		
		Properties prop = new Properties();

		if (propsFile != null) {
			String realPath = getServletContext().getRealPath(propsFile);
			if (realPath != null) {
				propsFile = realPath;
			}
			prop.load(inputStream);
		}
		
		/**这里是日志的配置*/
		String log = prop.getProperty(Velocity.RUNTIME_LOG);
		if (log != null) {
			log = getServletContext().getRealPath(log);
			if (log != null) {
				prop.setProperty(Velocity.RUNTIME_LOG, log);
			}
		}
		
		/**设置模板文件的位置*/
		String path = prop.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH);
		if (path != null) {
			path = getServletContext().getRealPath(path);
			if (path != null) {
				prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
			}
		}
		return prop;
	}

}
