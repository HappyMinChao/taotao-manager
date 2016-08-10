package com.taotao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	TbItemMapper itemMapper;
	
	@Override
	public TbItem getTbItem(long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public EasyUIDateGridResult getItemList(int pageNum, int pageSize) {
		PageHelper pageHelp = new PageHelper();
		//设置查询页面信息
		pageHelp.startPage(pageNum, pageSize);
		TbItemExample example  = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//把查询到的结果集设置进pageInfo
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//设置页面返回信息
		return new EasyUIDateGridResult(pageInfo.getTotal(),list);
	}

}
