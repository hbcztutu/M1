package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月25日 下午8:26:39 <br/>
 *
 * @author hbcztutu
 * @version 1.0
 * @Copyright (c) 2016,tuzhiming All Rights Reserved.
 */
public class register {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "provider.xml" });
		context.start();
		System.in.read(); // 按任意键退出
	}
}

