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
		
		//使用pageHelper前设置分页信息
		PageHelper.startPage(1, 20);
		
		//条件查询使用 ItemExample() 类
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = itemDao.selectByExample(itemExample);
		
		//把查询结果设置到pageInfo中
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//可以在pageInfo中获取到总条数和数据等信息
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
