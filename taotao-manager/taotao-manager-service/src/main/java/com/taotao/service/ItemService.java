package com.taotao.service;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	public abstract TbItem getTbItem(long itemId);
	public abstract EasyUIDateGridResult getItemList(int pageNum , int pageSize);
}
