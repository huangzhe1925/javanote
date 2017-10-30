package com.hz.javanote.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ProducerConsumerTest {

	@Autowired
	public ProducerService producerService;

	@Autowired
	@Qualifier("queueDestination")
	public Destination destination;

	public void testSend() {
		for (int i = 0; i < 2; i++) {
			producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i + 1));
		}
	}

	public static void main(String args[]) {
		ProducerConsumerTest producerConsumerTest;
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:/applicationcontext.xml");
		producerConsumerTest = ctx.getBean(ProducerConsumerTest.class);
		producerConsumerTest.testSend();
//		ctx.close();// 释放资源
	}

}