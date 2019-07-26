package com.eshop.dubbo.service;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.pojo.TbItemParam;

public interface TbItemParamDubboService {
	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return 包含：当前页显示数据和总条数
	 */
	EasyUIDataGrid showPage(int page,int rows);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	int delByIds(String ids) throws Exception;
	
	/**
	 * 根据类目id查询参数模板
	 * @param catId
	 * @return
	 */
	TbItemParam selByCatId(long catId);
	
	/**
	 * 新增模板
	 * @param tbItemParam
	 * @return
	 */
	int insParam(TbItemParam tbItemParam);
}
