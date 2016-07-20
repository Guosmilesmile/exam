package com.cnc.exam.auth.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;





import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnc.exam.auth.constant.Constants;
import com.cnc.exam.auth.entity.Resource;
import com.cnc.exam.auth.entity.User;
import com.cnc.exam.auth.service.ResourceService;
import com.cnc.exam.auth.service.UserService;
import com.cnc.exam.base.controller.BaseController;


@Controller
public class HomeController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/")
	public String home(Model model,HttpServletRequest request){
		
		Subject curUser=SecurityUtils.getSubject();
		Session session=curUser.getSession();	
		String username=(String) curUser.getPrincipal();
		User currentUser=userService.findByUsername(username);
		session.setAttribute(Constants.CURRENT_USER, currentUser);//将当前用户放入session
		session.setAttribute(Constants.CURRENT_USERNAME, username);
		List<Resource> resources=resourceService.findAll();//用于前端页面生成侧边栏
		request.setAttribute("resources", resources);
		if (currentUser.getLoginTime() != null) { //更新登陆时间
			currentUser.setLastLoginTime(currentUser.getLoginTime());
		}
		currentUser.setLoginTime(new Date());
		userService.update(currentUser);
		return "index";
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	
}
