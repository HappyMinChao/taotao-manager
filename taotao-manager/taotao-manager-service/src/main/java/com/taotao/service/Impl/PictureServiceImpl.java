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
	 * 上传图片处理服务实现类
	 * @author	duminchao
	 * @date	2016年8月10日22:22:22
	 * @version 1.0
	 */
	@Override
	public PictureResult upload(MultipartFile uploadFile) {
		//1.判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			return PictureResult.faill("上传图片为空");
		}
		//2.根据时间生成文件路径  /home/ftpuser/www/image/2016/01/01/
		String filePath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		String tempPath = filePath;
		//3.把文件名和扩展名分别获取到
		String[] names = uploadFile.getOriginalFilename().split(".");
		//String originalFileName = names[0];
		//4.获取扩展名
		String extendName = names[1];
		//上传的文件名重新生成
		//5.生成新文件名,可以使用uuid生成新文件名, UUID.randomUUID()
		String imageName = IDUtils.genImageName();  //使用工具类
		//6.生成的文件名和扩展名组合生成文件名
		String fileName = imageName + "." + extendName;
		
		InputStream input = null;
		try {
			input = uploadFile.getInputStream();
			//7.把图片上传到ftp服务器（图片服务器）,需要把ftp的参数配置到配置文件中,文件在服务器的存放路径，应该使用日期分隔的目录结构
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
