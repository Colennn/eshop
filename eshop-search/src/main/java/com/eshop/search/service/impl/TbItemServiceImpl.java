package com.eshop.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.TbItemChild;
import com.eshop.dubbo.service.TbItemCatDubboService;
import com.eshop.dubbo.service.TbItemDescDubboService;
import com.eshop.dubbo.service.TbItemDubboService;
import com.eshop.pojo.TbItem;
import com.eshop.pojo.TbItemCat;
import com.eshop.pojo.TbItemDesc;
import com.eshop.search.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	
	@Resource
	private CloudSolrClient solrClient;
	@Override
	public void init() throws SolrServerException, IOException {
		solrClient.deleteByQuery("*:*");
		solrClient.commit();
		//查询所有正常的商品
		List<TbItem> listItem = tbItemDubboServiceImpl.selAllByStatus((byte)1);
		for (TbItem item : listItem) {
			//商品对应的类目信息
			TbItemCat cat = tbItemCatDubboServiceImpl.selById(item.getCid());
			//商品对应的描述信息
			TbItemDesc desc = tbItemDescDubboServiceImpl.selByItemid(item.getId());
			SolrInputDocument doc = new SolrInputDocument();
			
			doc.setField("id", item.getId());
			doc.setField("item_title", item.getTitle());
			doc.setField("item_sell_point", item.getSellPoint());
			doc.setField("item_price",item.getPrice() );
			doc.setField("item_image", item.getImage());
			doc.setField("item_category_name", cat.getName());
			doc.setField("item_desc", desc.getItemDesc());
			doc.setField("item_updated", item.getUpdated());
			solrClient.add(doc);
		}
		solrClient.commit();
	}
	@Override
	public Map<String, Object> selByQuery(String query,int page,int rows) throws SolrServerException, IOException {
		SolrQuery params = new SolrQuery();
		//设置分页条件
		params.setStart(rows*(page-1));
		params.setRows(rows);
		//设置条件
		params.setQuery("item_keywords:"+query);
		//设置高亮
		params.setHighlight(true);
		params.addHighlightField("item_title");
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		//添加排序
		params.setSort("item_updated", ORDER.desc);
		
		QueryResponse response = solrClient.query(params);
		
		List<TbItemChild> listChild = new ArrayList<>();
		//未高亮内容
		SolrDocumentList listSolr = response.getResults();
		//高亮内容
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();
		
		for (SolrDocument doc : listSolr) {
			TbItemChild  child = new TbItemChild();
			
			child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			List<String> list = hh.get(doc.getFieldValue("id")).get("item_title");
			if(list!=null&&list.size()>0){
				child.setTitle(list.get(0));
			}else{
				child.setTitle(doc.getFieldValue("item_title").toString());
			}
			child.setPrice((Long)doc.getFieldValue("item_price"));
			Object image = doc.getFieldValue("item_image");
			child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
			child.setSellPoint(doc.getFieldValue("item_sell_point").toString());
			
			listChild.add(child);
		}
		
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("itemList", listChild);
		resultMap.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound()/rows:listSolr.getNumFound()/rows+1);
		
		
		return resultMap;
	}
	@Override
	public int add(Map<String,Object> map,String desc) throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", map.get("id"));
		doc.setField("item_title", map.get("title"));
		doc.setField("item_sell_point", map.get("sellPoint"));
		doc.setField("item_price", map.get("price"));
		doc.setField("item_image", map.get("image"));
		doc.setField("item_category_name", tbItemCatDubboServiceImpl.selById((Integer)map.get("cid")).getName());
		doc.setField("item_desc", desc);
		UpdateResponse response = solrClient.add(doc);
		solrClient.commit();
		if(response.getStatus()==0){
			return 1;
		}
		return 0;
	}
}
