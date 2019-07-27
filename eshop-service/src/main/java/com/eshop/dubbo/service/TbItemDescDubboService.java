package com.eshop.dubbo.service;

import com.eshop.pojo.TbItemDesc;

public interface TbItemDescDubboService {
	/**
	 * 新增
	 * @param itDesc
	 * @return
	 */
	int insDesc(TbItemDesc itDesc);
	
	/**
	 * 根据主键查询商品描述对象
	 * @param itemid
	 * @return
	 */
	TbItemDesc selByItemid(long itemid);
}
