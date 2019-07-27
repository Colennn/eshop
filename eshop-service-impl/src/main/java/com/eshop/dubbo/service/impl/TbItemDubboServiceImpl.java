package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.dubbo.service.TbItemDubboService;
import com.eshop.mapper.TbItemDescMapper;
import com.eshop.mapper.TbItemMapper;
import com.eshop.mapper.TbItemParamItemMapper;
import com.eshop.pojo.TbItem;
import com.eshop.pojo.TbItemDesc;
import com.eshop.pojo.TbItemExample;
import com.eshop.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemDubboServiceImpl implements TbItemDubboService{
	@Resource
	private TbItemMapper tbItemMapper;
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		//设置分页条件
		PageHelper.startPage(page,rows);
		//查询全部
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		
		//分页代码
		PageInfo<TbItem> pi = new PageInfo<>(list);
		
		//放到实体类
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		
		return datagrid;
	}

	@Override
	public int updItemStatus(TbItem tbItem) {
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	@Override
	public int insTbItem(TbItem tbItem) {
		return tbItemMapper.insert(tbItem);
	}

	@Override
	public int insTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws Exception {
		int index = 0;
		try {
			index = tbItemMapper.insertSelective(tbItem);
			index += tbItemDescMapper.insertSelective(tbItemDesc);
			index += tbItemParamItemMapper.insertSelective(tbItemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(index==3) {
			return 1;  
		}else {
			throw new Exception("新增失败，数据还原");
		}
	}

	@Override
	public List<TbItem> selAllByStatus(byte status) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}

	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
