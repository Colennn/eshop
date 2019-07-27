package com.eshop.dubbo.service;

import java.util.List;

import com.eshop.pojo.TbOrder;
import com.eshop.pojo.TbOrderItem;
import com.eshop.pojo.TbOrderShipping;

public interface TbOrderDubboService {
	/**
	 * 创建订单
	 * @param order
	 * @param list
	 * @param shipping
	 * @return
	 */
	int insOrder(TbOrder order,List<TbOrderItem> list,TbOrderShipping shipping) throws Exception;
}
