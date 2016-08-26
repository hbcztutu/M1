package test.batis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cn.uuu.pojo.User;
import com.cn.uuu.service.UserService;

public class TestMyBatis {
	
	
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
    @Resource(name="userServiceImpl") 
    private UserService userService = null;  
   // private UserServiceImpl userServiceImpl=null;
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
         User user = userService.getUserById(1);  
         System.out.println(user.getUsername());  
         logger.info("值："+user.getUsername());  
        logger.info(JSON.toJSONString(user));  
    }  
	
	
	
	
	
	

}
