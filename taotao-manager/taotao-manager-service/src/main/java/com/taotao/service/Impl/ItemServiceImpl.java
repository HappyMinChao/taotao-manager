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
		//���ò�ѯҳ����Ϣ
		pageHelp.startPage(pageNum, pageSize);
		TbItemExample example  = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//�Ѳ�ѯ���Ľ�������ý�pageInfo
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//����ҳ�淵����Ϣ
		return new EasyUIDateGridResult(pageInfo.getTotal(),list);
	}

}
