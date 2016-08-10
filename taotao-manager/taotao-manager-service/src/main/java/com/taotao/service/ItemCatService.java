package com.taotao.service;

import java.util.List;

import com.taotao.common.TreeNodeResult;


public interface ItemCatService {
	public List<TreeNodeResult> getItemCatByParentId(long parentId); 
}
