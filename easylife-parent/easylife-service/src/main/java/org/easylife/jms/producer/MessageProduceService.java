package org.easylife.jms.producer;

public interface MessageProduceService {
	
	/**
	 * 向指定队列中发送消息（点对点方式）
	 * @param queueName 队列名称
	 * @param message   消息内容
	 */
	void sendMessage(String queueName, String message);
	
	
	/**
	 * 向指定队列中发送消息
	 * @param queueName 队列名称
	 * @param message   消息内容
	 * @param pubSubDomain "true" for the Publish/Subscribe domain ({@link javax.jms.Topic Topics})
	 *                     "false" for the Point-to-Point domain ({@link javax.jms.Queue Queues})
	 */
	void sendMessage(String queueName, String message, boolean pubSubDomain);

}
