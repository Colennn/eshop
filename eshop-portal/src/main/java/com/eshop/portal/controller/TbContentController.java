package com.eshop.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.portal.service.TbContentService;

@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("showBigPic")
	public String showBigPic(Model model) {
		model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
		return "index";
	}
}
