package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.dubbo.service.TbItemCatDubboService;
import com.eshop.mapper.TbItemCatMapper;
import com.eshop.pojo.TbItemCat;
import com.eshop.pojo.TbItemCatExample;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService{
	
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> show(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}

	@Override
	public TbItemCat selById(long id) {
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
