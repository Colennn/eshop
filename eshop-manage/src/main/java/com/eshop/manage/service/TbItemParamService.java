package com.eshop.manage.service;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.pojo.TbItemParam;

public interface TbItemParamService {
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
	int delete(String ids) throws Exception;
	
	/**
	 * 根据类目id查询模板信息
	 * @param catId
	 * @return
	 */
	EgoResult showParam(long catId);
	
	/**
	 * 新增模板信息
	 * @param tbItemParam
	 * @return
	 */
	EgoResult save(TbItemParam tbItemParam);
}
