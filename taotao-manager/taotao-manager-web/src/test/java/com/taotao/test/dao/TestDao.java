package com.taotao.test.dao;

import java.util.List;

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
	
	@Before
	public void init(){
		ioc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}

	@Test
	public void testPageHelper(){
		TbItemMapper itemDao = ioc.getBean(TbItemMapper.class);
		
		//ʹ��pageHelperǰ���÷�ҳ��Ϣ
		PageHelper.startPage(1, 20);
		
		//������ѯʹ�� ItemExample() ��
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = itemDao.selectByExample(itemExample);
		
		//�Ѳ�ѯ������õ�pageInfo��
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//������pageInfo�л�ȡ�������������ݵ���Ϣ
		long totalNumber = pageInfo.getTotal();
		List<TbItem> list2 = pageInfo.getList();
		for (TbItem tbItem : list2) {
			System.out.println(tbItem);
		}
		
	/*	@After
		public void destory(){
			System.out.println("destory");
		}*/
		
	}
	
}
