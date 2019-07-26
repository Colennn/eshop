package com.eshop.dubbo.service.impl;

import javax.annotation.Resource;

import com.eshop.dubbo.service.TbItemDescDubboService;
import com.eshop.mapper.TbItemDescMapper;
import com.eshop.pojo.TbItemDesc;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService{
	
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public int insDesc(TbItemDesc itDesc) {
		return tbItemDescMapper.insert(itDesc);
	}

}
