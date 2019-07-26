package com.eshop.dubbo.service;

import java.util.List;

import com.eshop.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	
	/**
	 * 根据父id查询所有子类目
	 * @param id
	 * @return
	 */
	List<TbContentCategory> selByPid(long id);
	
	/**
	 * 新增
	 * @param cate
	 * @return
	 */
	int insTbContentCategory(TbContentCategory cate);
	
	/**
	 * 修改IsParent通过id
	 * @param cate
	 * @return
	 */
	int updIsParentById(TbContentCategory cate);
	
	/**
	 * 通过id查询内容类目详细信息
	 * @param id
	 * @return
	 */
	TbContentCategory selById(long id);
	
}
