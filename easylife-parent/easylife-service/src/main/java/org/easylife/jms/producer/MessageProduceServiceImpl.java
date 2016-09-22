package org.easylife.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


@Component
public class MessageProduceServiceImpl implements MessageProduceService{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(String queueName, String message, boolean pubSubDomain) {
		try {
			logger.error("sendMessage > message", message);
			
			jmsTemplate.setPubSubDomain(pubSubDomain);
			//设置消息
			jmsTemplate.send(queueName, this.createTxtMsg(message));
			
			logger.error("sendMessage", "end...");
			
		} catch (Exception e) {
			logger.error("MessageProduceServiceImpl#sendMessage出现异常，异常信息：", e.getMessage());
		}
	}
	
	public void sendMessage(String queueName, String message) {
		this.sendMessage(queueName, message, false);
	}

	private MessageCreator createTxtMsg(final String msg) {
		return new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(msg);
				return textMessage;
			}
		};
	}

}
