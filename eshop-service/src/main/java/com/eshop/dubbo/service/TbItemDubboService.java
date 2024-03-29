package com.eshop.dubbo.service;

import java.util.List;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.pojo.TbItem;
import com.eshop.pojo.TbItemDesc;
import com.eshop.pojo.TbItemParamItem;

public interface TbItemDubboService {
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updItemStatus(TbItem tbItem);
	
	/**
	 * 商品新增
	 * @param tbItem
	 * @return
	 */
	int insTbItem(TbItem tbItem);
	
	/**
	 * 包含商品表和商品描述表 
	 * @param tbItem
	 * @param tbItemDesc
	 * @param tbItemParamItem
	 * @return
	 * @throws Exception 
	 */
	int insTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws Exception;
	
	/**
	 * 通过状态查询全部可用数据
	 * @return
	 */
	List<TbItem> selAllByStatus(byte status);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TbItem selById(long id);
}
