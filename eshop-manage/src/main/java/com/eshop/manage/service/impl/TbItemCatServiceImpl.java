package com.eshop.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.EasyUiTree;
import com.eshop.dubbo.service.TbItemCatDubboService;
import com.eshop.manage.service.TbItemCatService;
import com.eshop.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	
	@Override
	public List<EasyUiTree> show(long pid){
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
		List<EasyUiTree> listTree = new ArrayList<>();
		for (TbItemCat cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setState(cat.getIsParent()?"closed":"open");
			tree.setText(cat.getName());
			listTree.add(tree);
		}
		return listTree;
	}
	
}
