package com.eshop.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.manage.service.TbItemParamService;
import com.eshop.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	@Resource
	private TbItemParamService tbItemParamServiceImpl;
	
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page,int rows) {
		return tbItemParamServiceImpl.showPage(page, rows);
	}
	
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		EgoResult er = new EgoResult();
		try {
			int index = tbItemParamServiceImpl.delete(ids);
			if(index==1) {
				er.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			er.setData(e.getMessage());
		}
		return er;
	}
	
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult show(@PathVariable long catId) {
		return tbItemParamServiceImpl.showParam(catId);
	}
	
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public EgoResult save(TbItemParam tbItemParam,@PathVariable long catId) {
		tbItemParam.setItemCatId(catId);
		return tbItemParamServiceImpl.save(tbItemParam);
	}
	
}
