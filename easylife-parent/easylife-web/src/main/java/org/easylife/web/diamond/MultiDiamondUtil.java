package org.easylife.web.diamond;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.MapUtils;
import org.easylife.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.taobao.diamond.manager.ManagerListenerAdapter;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

/**
 * 
 * @Filename MultiDiamondUtil.java
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
 * @Date: 2016年6月3日 下午4:08:25
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class MultiDiamondUtil implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(MultiDiamondUtil.class);
	private static final long TIME_OUT = 5000L;
	private static final String separator = ",";
	private String diamondIpList;
	List<DiamondBean> diamondBeanList;
	private String filePath;
	private String configContent;
	private Properties properties;
	private Map configMap;

	public void afterPropertiesSet() throws Exception {
		this.init();
	}

	private void init() {
		ManagerListenerAdapter diamondListener = new ManagerListenerAdapter() {
			public void receiveConfigInfo(String configInfo) {
				MultiDiamondUtil.this.configContent = configInfo;
				MultiDiamondUtil.this.setConfigMap();
			}
		};
		if (org.apache.commons.lang.StringUtils.isNotEmpty(filePath)) {
			FileConfigUtil fileConfigUtil = new FileConfigUtil(System.getProperty("user.home") + "/.diamond.domain");
			diamondIpList = fileConfigUtil.getValue();
		}
		this.log.info(
				"diaond-->filePath:" + System.getProperty("user.home") + " change diamondIpList:" + diamondIpList);
		if (diamondBeanList != null && diamondIpList != null) {
			for (DiamondBean diamondBean : diamondBeanList) {
				if (!StringUtils.isEmpty(diamondBean.getDataId()) && !StringUtils.isEmpty(diamondBean.getGroup())) {
					DefaultDiamondManager manager = new DefaultDiamondManager(diamondBean.getGroup(),
							diamondBean.getDataId(), diamondListener, true);

					this.configContent = manager.getAvailableConfigureInfomation(TIME_OUT);
					this.setConfigMap();
				} else {
					this.log.error("diamond数据配置properties异常: DataId:" + diamondBean.getDataId() + ",Group:"
							+ diamondBean.getGroup());
				}
			}
		} else {
			this.log.error("diamond数据配置properties异常: diamondBeanList is null or diamondIpList is null");
		}
	}

	private void setConfigMap() {
		if (org.apache.commons.lang.StringUtils.isNotEmpty(this.configContent)) {
			if (this.properties == null) {
				this.properties = new Properties();
			}

			try {
				this.properties.load(new StringReader(this.configContent));
				this.configMap = this.properties;
			} catch (IOException var2) {
				this.log.error("diamond数据流转成properties异常" + var2.getMessage());
			}
		}

	}

	public Object getValue(Object key) {
		return MapUtils.isEmpty(this.configMap) ? null : this.configMap.get(key);
	}

	public List<String> getValues(Object key) {
		String text = (String) this.getValue(key);
		if (org.apache.commons.lang.StringUtils.isNotEmpty(text)) {
			String[] strArrays = text.split(",");
			return Arrays.asList(strArrays);
		} else {
			return null;
		}
	}

	public Map<String, String> getValueMap(Object key) {
		String text = (String) this.getValue(key);
		if (org.apache.commons.lang.StringUtils.isNotEmpty(text)) {
			String[] strArrays = text.split(",");
			HashMap aMap = new HashMap(strArrays.length);
			String[] arr$ = strArrays;
			int len$ = strArrays.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				String str1 = arr$[i$];
				if (!org.apache.commons.lang.StringUtils.isNotEmpty(str1)) {
					this.log.error("MapPropertyEditorSupport error,text=" + text);
					return null;
				}

				String[] strArrays2 = str1.split("=");
				if (strArrays2.length != 2) {
					this.log.error("MapPropertyEditorSupport error,text=" + text);
					return null;
				}

				aMap.put(strArrays2[0], strArrays2[1]);
			}

			return aMap;
		} else {
			return null;
		}
	}

	public String getDiamondIpList() {
		return diamondIpList;
	}

	public void setDiamondIpList(String diamondIpList) {
		this.diamondIpList = diamondIpList;
	}

	public String getConfigContent() {
		return configContent;
	}

	public void setConfigContent(String configContent) {
		this.configContent = configContent;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Map getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map configMap) {
		this.configMap = configMap;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<DiamondBean> getDiamondBeanList() {
		return diamondBeanList;
	}

	public void setDiamondBeanList(List<DiamondBean> diamondBeanList) {
		this.diamondBeanList = diamondBeanList;
	}
}
