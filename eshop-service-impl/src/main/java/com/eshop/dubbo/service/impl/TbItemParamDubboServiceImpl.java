package com.eshop.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.dubbo.service.TbItemParamDubboService;
import com.eshop.mapper.TbItemParamMapper;
import com.eshop.pojo.TbItemParam;
import com.eshop.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{
	
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		//先设置分页查询
		PageHelper.startPage(page,rows);
		
		//查询全部
		//如果表中有一个或一个以上的列是text类型，生成的方法xxxxxWithBlobs()
		//如果使用xxxxxWithBlobs()查询结果中包含带有text类型的列，如果没有使用withblobs()方法不带有text类型
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		
		//根据程序员自己编写的SQL语句结合分页插件产生最终结果，封装到PageInfo
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		
		//设置返回方法
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		
		return datagrid;
	}

	@Override
	public int delByIds(String ids) throws Exception {
		String[] idStr = ids.split(",");
		int index = 0;
		for (String id : idStr) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index==idStr.length) {
			return 1;
		}else {
			throw new Exception("删除失败，可能原因：数据已经不存在");
		}
	}

	@Override
	public TbItemParam selByCatId(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		if(list!=null&&list.size()>0) {
			//要求每个类目只能有一个模板
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insParam(TbItemParam tbItemParam) {
		return tbItemParamMapper.insert(tbItemParam);
	}

}
