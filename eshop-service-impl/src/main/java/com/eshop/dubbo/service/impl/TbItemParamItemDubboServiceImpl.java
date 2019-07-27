package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.dubbo.service.TbItemParamItemDubboSerice;
import com.eshop.mapper.TbItemParamItemMapper;
import com.eshop.pojo.TbItemParamItem;
import com.eshop.pojo.TbItemParamItemExample;

public class TbItemParamItemDubboServiceImpl  implements TbItemParamItemDubboSerice{
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public TbItemParamItem selByItemid(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
