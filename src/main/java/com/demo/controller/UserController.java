package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
	@RequestMapping("/query.action")
	public String itemMenu(Model model) {
		model.addAttribute("info", "欢迎来到 用户管理模拟  界面");
		return "info";
	}

	@RequestMapping("/add.action")
	public String itemadd(Model model) {
		model.addAttribute("info", "欢迎来到 用户新增模拟  界面");
		return "info";
	}

	@RequestMapping("/edit.action")
	public String itemedit(Model model) {
		model.addAttribute("info", "欢迎来到 用户修改模拟  界面");
		return "info";
	}

	@RequestMapping("/delete.action")
	public String itemdelete(Model model) {
		model.addAttribute("info", "欢迎来到 用户删除模拟  界面");
		return "info";
	}
}
