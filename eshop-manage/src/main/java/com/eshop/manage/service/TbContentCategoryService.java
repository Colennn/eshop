package com.eshop.manage.service;

import java.util.List;

import com.eshop.commons.pojo.EasyUiTree;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.pojo.TbContentCategory;

public interface TbContentCategoryService {
	
	/**
	 * 查询所有的类目并转换成easyUI tree的属性要求
	 * @param id 
	 * @return
	 */
	List<EasyUiTree> showCategory(long id);
	
	/**
	 * 类目新增
	 * @return
	 */
	EgoResult create(TbContentCategory cate);
	/**
	 * 类目重命名
	 * @param cate
	 * @return
	 */
	EgoResult update(TbContentCategory cate);
	/**
	 * 删除类目
	 * @param id
	 * @return
	 */
	EgoResult delete(TbContentCategory cate);
}
