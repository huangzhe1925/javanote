package com.hz.javanote.springbatch.schedulerexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static void main(String[] args) {

		String springConfig = "springbatch/schedulertask/spring/batch/jobs/job-report.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

	}
}
