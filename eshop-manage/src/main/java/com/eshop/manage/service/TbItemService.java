package com.eshop.manage.service;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.pojo.TbItem;

public interface TbItemService {
	/**
	 * 商品分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	/**
	 * 批量修改商品状态
	 * @param ids
	 * @param status
	 * @return
	 */
	int update(String ids,byte status);
	
	/**
	 * 商品新增
	 * @return
	 */
	int save(TbItem tbItem,String desc,String itemParams);
}
