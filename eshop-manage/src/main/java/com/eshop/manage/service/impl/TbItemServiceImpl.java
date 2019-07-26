package com.eshop.manage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.commons.utils.IDUtils;
import com.eshop.dubbo.service.TbItemDescDubboService;
import com.eshop.dubbo.service.TbItemDubboService;
import com.eshop.manage.service.TbItemService;
import com.eshop.pojo.TbItem;
import com.eshop.pojo.TbItemDesc;
import com.eshop.pojo.TbItemParamItem;

@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	
	/**
	 * 查询商品
	 */
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboServiceImpl.show(page, rows);
	}
	/**
	 * 商品上架、下架、删除等功能
	 */
	@Override
	public int update(String ids, byte status) {
		int index = 0;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for(String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index += tbItemDubboServiceImpl.updItemStatus(item);
		}
		if(index==idsStr.length) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 商品新增
	 */
	@Override
	public int save(TbItem item, String desc,String itemParams) {
		//未考虑事务回滚的写法
//		long id = IDUtils.genItemId();
//		item.setId(id);
//		Date date = new Date();
//		item.setCreated(date);
//		item.setUpdated(date);
//		item.setStatus((byte)1);
//		int index = tbItemDubboService.insTbItem(item);
//		if(index>0) {
//			TbItemDesc itemDesc = new TbItemDesc();
//			itemDesc.setItemDesc(desc);
//			itemDesc.setItemId(id);
//			itemDesc.setCreated(date);
//			itemDesc.setUpdated(date);
//			index+=tbItemDescDubboService.insDesc(itemDesc);
//		}
//		if(index==2) {
//			return 1;
//		}
//		return 0;
		
		//考虑了事务回滚
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte)1);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItem.setParamData(itemParams);
		paramItem.setItemId(id);
		
		
		int index = 0;
		
		try {
			index = tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc, paramItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return index;
	}

}
