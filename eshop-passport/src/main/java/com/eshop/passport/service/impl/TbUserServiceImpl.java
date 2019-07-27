package com.eshop.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eshop.commons.pojo.EgoResult;
import com.eshop.commons.utils.CookieUtils;
import com.eshop.commons.utils.JsonUtils;
import com.eshop.dubbo.service.TbUserDubboService;
import com.eshop.pojo.TbUser;
import com.eshop.redis.dao.JedisDao;
import com.eshop.passport.service.TbUserService;

@Service
public class TbUserServiceImpl implements TbUserService {
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Override
	public EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
		EgoResult er = new EgoResult();
		TbUser userSelect = tbUserDubboServiceImpl.selByUser(user);
		if(userSelect!=null){
			er.setStatus(200);
			//当用户登录成功后把用户信息放入到redis中
			String key = UUID.randomUUID().toString();
			jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
			jedisDaoImpl.expire(key, 60*60*24*7);
			//产生Cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60*60*24*7);
		}else{
			er.setMsg("用户名和密码错误");
		}
		return er;
	}
	@Override
	public EgoResult getUserInfoByToken(String token) {
		EgoResult er = new EgoResult();
		String json = jedisDaoImpl.get(token);
		if(json!=null&&!json.equals("")){
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
			//可以把密码清空
			tbUser.setPassword(null);
			er.setStatus(200);
			er.setMsg("OK");
			er.setData(tbUser);
		}else{
			er.setMsg("获取失败");
		}
		
		return er;
	}
	@Override
	public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
		Long index = jedisDaoImpl.del(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		EgoResult er = new EgoResult();
		er.setStatus(200);
		er.setMsg("OK");
		return er;
	}

}
