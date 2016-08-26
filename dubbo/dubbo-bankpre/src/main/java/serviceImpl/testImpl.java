package main.java.serviceImpl;

import main.java.service.testInterface;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月25日 下午8:25:28 <br/>
 *
 * @author hbcztutu
 * @version 1.0
 * @Copyright (c) 2016,tuzhiming All Rights Reserved.
 */
public class testImpl implements testInterface {

	@Override
	public boolean test(String str) {

		if (str.equals("0")) {
			return true;
		}
		return false;
	}

}

