package org.easylife.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @Filename SyncReceiveMessage.java
 * 
 * @Description	同步接收消息
 * 
 * @Version 1.0
 * 
 * @Author xpangxie
 * 
 * @Email xpangxie@yiji.com
 * 
 * @History Author: xpangxie
 * 
 * @Date: 2016年5月27日 下午4:08:11
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
@Component
public class SyncReceiveMessage {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void receive(){
		
		Object message = jmsTemplate.receiveAndConvert();
		System.out.println("message:"+(String)message);
	}

}
