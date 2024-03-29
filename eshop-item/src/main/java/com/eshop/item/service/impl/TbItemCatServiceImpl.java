package com.eshop.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.item.pojo.PortalMenu;
import com.eshop.item.pojo.PortalMenuNode;
import com.eshop.item.service.TbItemCatService;
import com.eshop.dubbo.service.TbItemCatDubboService;
import com.eshop.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public PortalMenu showCatMenu() {
		//查询出所有一级菜单
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		PortalMenu pm =new PortalMenu();
		pm.setData(selAllMenu(list));
		return pm;
	}
	
	/**
	 * 最终返回结果所有查询到的结果
	 * @param list
	 * @return
	 */
	public List<Object> selAllMenu(List<TbItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			if(tbItemCat.getIsParent()){
				PortalMenuNode pmd  = new PortalMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
				listNode.add(pmd);
			}else{
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return listNode;
	}

}
