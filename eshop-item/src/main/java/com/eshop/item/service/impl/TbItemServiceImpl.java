package com.eshop.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.TbItemChild;
import com.eshop.commons.utils.JsonUtils;
import com.eshop.dubbo.service.TbItemDubboService;
import com.eshop.item.service.TbItemService;
import com.eshop.pojo.TbItem;
import com.eshop.redis.dao.JedisDao;

@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	@Override
	public TbItemChild show(long id) {
		String key = itemKey+id;
		if(jedisDaoImpl.exists(key)){
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")){
				return JsonUtils.jsonToPojo(json, TbItemChild.class);
			}
		}
		TbItem item = tbItemDubboServiceImpl.selById(id);
		TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);
		//存到数据库中
		jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
		return child;
	}

}
