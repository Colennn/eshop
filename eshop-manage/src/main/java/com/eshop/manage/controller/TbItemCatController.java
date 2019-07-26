package com.eshop.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.commons.pojo.EasyUiTree;
import com.eshop.manage.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	
	/**
	 * 显示商品类目
	 * @param pid
	 * @return
	 */
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showCat(@RequestParam(defaultValue="0") long id) {
		return tbItemCatServiceImpl.show(id);
	}
}
