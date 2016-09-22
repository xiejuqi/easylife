package org.easylife.web.diamond;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * 
 * @Filename DiamondMultiPropertyPlaceholderConfigurer.java
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
 * @Date: 2016年6月3日 下午4:07:31
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class DiamondMultiPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static final Logger log = LoggerFactory.getLogger(DiamondMultiPropertyPlaceholderConfigurer.class);
	private MultiDiamondUtil multiDiamondUtil;

	public DiamondMultiPropertyPlaceholderConfigurer() {
	}

	public MultiDiamondUtil getMultiDiamondUtil() {
		return multiDiamondUtil;
	}

	public void setMultiDiamondUtil(MultiDiamondUtil multiDiamondUtil) {
		this.multiDiamondUtil = multiDiamondUtil;
	}

	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
		Map configMap = this.multiDiamondUtil.getConfigMap();
		if (MapUtils.isEmpty(configMap)) {
			throw new IllegalStateException("从diamond取出数据为空，请检查数据配置");
		} else {
			log.info("diamond 获取到数据:" + configMap);
			Iterator i$ = configMap.keySet().iterator();

			while (i$.hasNext()) {
				String key = (String) i$.next();
				props.setProperty(key, (String) configMap.get(key));
			}

			super.processProperties(beanFactoryToProcess, props);
		}
	}

}
