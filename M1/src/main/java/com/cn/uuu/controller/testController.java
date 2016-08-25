package com.cn.uuu.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cn.uuu.pojo.User;
import com.cn.uuu.service.UserService;



@Controller
@RequestMapping("/test")
public class testController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(value="/showuser.do", method = RequestMethod.GET)
	public String toIndex(HttpServletRequest request,Model model){
		User userLogin=new User();
		userLogin.setUsername(request.getParameter("username"));
		userLogin.setPassword(request.getParameter("password"));
		
		 User user=new User();

		if(this.userService.getLoginUser(userLogin)){
          user=this.userService.getUserByusername(userLogin.getUsername());
          System.out.println(user.toString()+"get user success");
          model.addAttribute("user", user);
          
		}else{
			
			model.addAttribute("tip","user can not in use");
			System.out.println("can not get user");
		}
	      	return "indexuser";
	}
	
	
	
	@RequestMapping(value="/register.do",method= RequestMethod.POST)
	public String registerIndex(HttpServletRequest request,Model model){
		
		//ModelAndView mav=new ModelAndView();

		User userRegister=new User();
		String username2=request.getParameter("username");
		String password2=request.getParameter("password");
		userRegister.setUsername(username2);
		userRegister.setPassword(password2);
		System.out.println(username2+"密码是"+password2);
		
		if(userRegister.getUsername()!=null){
			if (request.getParameter("age")==null) {
				userRegister.setAge(25);
			}else{
				userRegister.setAge(Integer.parseInt(request.getParameter("age")));
			}
			System.out.println(userRegister.getUsername()+" 显示注册的名字");
			
		}else{
			
			User user2 = new User();  
			user2.setUsername("pm");  
			user2.setPassword("pm");  
			user2.setAge(45);  			
			userRegister=user2;			
		}
		
		int id=this.userService.inster(userRegister);

        User user=this.userService.getUserById(id);
        
        System.out.println(user.toString()+"insert user success");
        
        model.addAttribute("user", user);
		
	    return "indexuser";
	}

	


	
	
}
