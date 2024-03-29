package com.eshop.manage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicService {
	/**
	 * 图片上传
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	Map<String, Object> upload(MultipartFile file) throws IOException;
}
