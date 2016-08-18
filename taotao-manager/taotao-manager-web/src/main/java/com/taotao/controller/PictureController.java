package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;


@Controller
@RequestMapping(value="/pic")
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile){
		PictureResult result = pictureService.upload(uploadFile);
		return result;
	}
	
}
