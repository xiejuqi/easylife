package org.easylife.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 * @Filename EasylifeMessageListener.java
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
 * @Date: 2016年5月27日 下午2:22:12
 * 
 * @Version: 1.0
 * 
 * @Content: create
 * 
 */
public class EasylifeMessageListener implements MessageListener	{

	public void onMessage(Message message) {
		
		//这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换  
        TextMessage textMsg = (TextMessage) message;  
        
        System.out.println("Queue接收到一个纯文本消息。");  
        
        try {  
            System.out.println("Queue消息内容是：" + textMsg.getText());  
        } catch (JMSException e) {  
            e.printStackTrace();  
        } 
	}

}
