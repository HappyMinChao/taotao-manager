package com.taotao.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

@Transactional
public interface PictureService {
	public abstract PictureResult upload(MultipartFile uploadFile);
}
