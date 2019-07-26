package com.eshop.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.dubbo.service.TbItemCatDubboService;
import com.eshop.dubbo.service.TbItemParamDubboService;
import com.eshop.manage.pojo.TbItemParamChild;
import com.eshop.manage.service.TbItemParamService;
import com.eshop.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{
	
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid datagrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) datagrid.getRows();
		List<TbItemParamChild> listChild = new ArrayList<>();
		for (TbItemParam param : list) {
			TbItemParamChild child = new TbItemParamChild();
			child.setCreated(param.getCreated());
			child.setId(param.getId());
			child.setItemCatId(param.getItemCatId());
			child.setParamData(param.getParamData());
			child.setUpdated(param.getUpdated());
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(child.getItemCatId()).getName());
			listChild.add(child);
		}
		datagrid.setRows(listChild);
		return datagrid;
	}
	
	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}

	@Override
	public EgoResult showParam(long catId) {
		EgoResult er = new EgoResult();
		TbItemParam param = tbItemParamDubboServiceImpl.selByCatId(catId);
		if(param!=null) {
			er.setStatus(200);
			er.setData(param);
		}
		return er;
	}

	@Override
	public EgoResult save(TbItemParam tbItemParam) {
		Date date = new Date();
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		int index = tbItemParamDubboServiceImpl.insParam(tbItemParam);
		EgoResult er = new EgoResult();
		if(index>0) {
			er.setStatus(200);
		}
		return er;
	}

}
