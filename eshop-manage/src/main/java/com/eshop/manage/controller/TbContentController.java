package com.eshop.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.pojo.TbContent;
import com.eshop.manage.service.TbContentService;

@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	
	/**
	 * 显示内容信息
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showContent(long categoryId,int page,int rows){
		return tbContentServiceImpl.showContent(categoryId, page, rows);
	}
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult save(TbContent content){
		EgoResult er = new EgoResult();
		int index = tbContentServiceImpl.save(content);
		if(index>0){
			er.setStatus(200);
		}
		return er;
	}
}	
