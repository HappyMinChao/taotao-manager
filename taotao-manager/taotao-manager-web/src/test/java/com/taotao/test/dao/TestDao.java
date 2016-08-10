/*package com.taotao.test.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestDao {
	
	ApplicationContext ioc;
	
	//@Before
	public void init(){
		ioc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
	}

	@Test
	public void testPageHelper(){
		TbItemMapper itemDao = ioc.getBean(TbItemMapper.class);
		
		//设置pageHelper分页信息
		PageHelper.startPage(1, 20);
		
		//创建ItemExample()用来条件查询
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = itemDao.selectByExample(itemExample);
		
		//�Ѳ�ѯ������õ�pageInfo��
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//������pageInfo�л�ȡ�����������ݵ���Ϣ
		long totalNumber = pageInfo.getTotal();
		List<TbItem> list2 = pageInfo.getList();
		for (TbItem tbItem : list2) {
			System.out.println(tbItem);
		}
		
	}
	@Test
	public void testFtp(){
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect("192.168.1.104");
			ftpClient.login("ftpuser", "ftpuser");
			FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\spring容器和springMVC容器关系.png");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.storeFile("123.png", fis);
			fis.close();
			ftpClient.logout();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
*/