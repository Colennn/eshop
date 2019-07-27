package com.eshop.dubbo.service;

import com.eshop.pojo.TbUser;

public interface TbUserDubboService {
	/**
	 * 根据用户名和密码查询登录
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
}
