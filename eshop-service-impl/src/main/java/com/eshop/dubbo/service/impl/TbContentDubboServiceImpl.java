package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.dubbo.service.TbContentDubboService;
import com.eshop.mapper.TbContentMapper;
import com.eshop.pojo.TbContent;
import com.eshop.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbContentDubboServiceImpl implements TbContentDubboService {
	
	@Resource
	private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		
		PageHelper.startPage(page, rows);
		
		TbContentExample example = new TbContentExample();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> pi = new PageInfo<>(list);
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}

	@Override
	public int insContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}

	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		//排序
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}

}
