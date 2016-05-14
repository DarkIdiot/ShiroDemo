package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.exception.CustomException;
import com.demo.model.ActiveUser;
import com.demo.service.SysService;


@Controller
public class LoginController {
	
	@Autowired
	private SysService sysService;

	//用户登陆提交
	@RequestMapping("/login")
	public String loginsubmit(HttpSession session,String username,String password,String randomcode) throws Exception{

		//校验验证码
		//从session获取正确的验证码
		String validateCode = (String)session.getAttribute("validateCode");
		if(!randomcode.equals(validateCode)){
			//抛出异常：验证码错误
			throw new CustomException("验证码错误 !");
		}
		//用户身份认证
		ActiveUser activeUser = sysService.authenticate(username, password);
		
		//记录session
		session.setAttribute("activeUser", activeUser);
		
		return "redirect:first.action";
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		//清空session
		session.invalidate();
		
		return "redirect:first.action";
	}
}
