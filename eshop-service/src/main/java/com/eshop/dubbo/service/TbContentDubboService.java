package com.eshop.dubbo.service;

import java.util.List;

import com.eshop.commons.pojo.EasyUIDataGrid;
import com.eshop.pojo.TbContent;

public interface TbContentDubboService {
	/**
	 * 分页查询
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selContentByPage(long categoryId,int page,int rows);
	/**
	 * 新增
	 * @param content
	 * @return
	 */
	int insContent(TbContent content);
	
	/**
	 * 查询出最近的前n个
	 * @param count
	 * @return
	 */
	List<TbContent> selByCount(int count,boolean isSort);
}
