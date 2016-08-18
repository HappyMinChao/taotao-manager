package com.taotao.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${FTP_IP_ADDRESS}")
	private String host;
	@Value("${FTP_PORT}")
	private Integer port;
	@Value("${FTP_USER_NAME}")
	private String username;
	@Value("${FTP_PASSWORD}")
	private String password;
	@Value("${FTP_BASE_PATH}")
	private String basePath;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	/**
	 * �ϴ�ͼƬ�������ʵ����
	 * @author	duminchao
	 * @date	2016��8��10��22:22:22
	 * @version 1.0
	 */
	@Override
	public PictureResult upload(MultipartFile uploadFile) {
		//1.�ж��ϴ�ͼƬ�Ƿ�Ϊ��
		if (null == uploadFile || uploadFile.isEmpty()) {
			return PictureResult.faill("�ϴ�ͼƬΪ��");
		}
		//2.����ʱ�������ļ�·��  /home/ftpuser/www/image/2016/01/01/
		String filePath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		String tempPath = filePath;
		//3.���ļ�������չ���ֱ��ȡ��
		String[] names = uploadFile.getOriginalFilename().split(".");
		//String originalFileName = names[0];
		//4.��ȡ��չ��
		String extendName = names[1];
		//�ϴ����ļ�����������
		//5.�������ļ���,����ʹ��uuid�������ļ���, UUID.randomUUID()
		String imageName = IDUtils.genImageName();  //ʹ�ù�����
		//6.���ɵ��ļ�������չ����������ļ���
		String fileName = imageName + "." + extendName;
		
		InputStream input = null;
		try {
			input = uploadFile.getInputStream();
			//7.��ͼƬ�ϴ���ftp��������ͼƬ��������,��Ҫ��ftp�Ĳ������õ������ļ���,�ļ��ڷ������Ĵ��·����Ӧ��ʹ�����ڷָ���Ŀ¼�ṹ
			boolean isSuccess = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, input);
		} catch (IOException e) {
			return PictureResult.faill(ExceptionUtil.getStackTrace(e));
		}finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return PictureResult.success(IMAGE_BASE_URL + filePath + "/" + fileName);
	}

}
