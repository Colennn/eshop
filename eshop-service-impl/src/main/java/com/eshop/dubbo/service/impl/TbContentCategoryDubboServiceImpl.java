package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.dubbo.service.TbContentCategoryDubboService;
import com.eshop.mapper.TbContentCategoryMapper;
import com.eshop.pojo.TbContentCategory;
import com.eshop.pojo.TbContentCategoryExample;

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService{
	@Resource
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<TbContentCategory> selByPid(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}

	@Override
	public int insTbContentCategory(TbContentCategory cate) {
		return tbContentCategoryMapper.insertSelective(cate);
	}

	@Override
	public int updIsParentById(TbContentCategory cate) {
		return tbContentCategoryMapper.updateByPrimaryKey(cate);
	}

	@Override
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

}
