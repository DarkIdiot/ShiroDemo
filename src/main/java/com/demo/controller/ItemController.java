package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("item")
public class ItemController {
	@RequestMapping("/query.action")
	public String itemMenu(Model model) {
		model.addAttribute("info", "欢迎来到 商品管理模拟  界面");
		return "info";
	}

	@RequestMapping("/add.action")
	public String itemadd(Model model) {
		model.addAttribute("info", "欢迎来到 商品新增模拟  界面");
		return "info";
	}

	@RequestMapping("/edit.action")
	public String itemedit(Model model) {
		model.addAttribute("info", "欢迎来到 商品修改模拟  界面");
		return "info";
	}

	@RequestMapping("/delete.action")
	public String itemdelete(Model model) {
		model.addAttribute("info", "欢迎来到 商品删除模拟  界面");
		return "info";
	}

	@RequestMapping("/queryItem.action")
	public String itemQueryItem(Model model) {
		model.addAttribute("info", "欢迎来到 商品查询模拟  界面");
		return "info";
	}
}
