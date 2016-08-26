package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.java.service.testInterface;






/**
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月25日 下午8:59:27 <br/>
 *
 * @author hbcztutu
 * @version 1.0
 * @Copyright (c) 2016,tuzhiming All Rights Reserved.
 */
public class testmain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "consumer.xml" });
		context.start();
		testInterface test = (testInterface)context.getBean("testInterface"); // 获取远程服务代理
	    boolean hello = test.test("1"); // 执行远程方法
	    context.close();
		System.out.println(hello);
	}

}

