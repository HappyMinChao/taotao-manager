package com.taotao.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.TreeNodeResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	TbItemCatMapper itemCatMapper; 
	
	@Override
	public List<TreeNodeResult> getItemCatByParentId(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> listCat = itemCatMapper.selectByExample(example);
		
		List<TreeNodeResult> listTreeNode = new ArrayList<>();
		//把查询出来的TbItemCat对象转换成TreeNode对象
		for (TbItemCat tbItemCat : listCat) {
			TreeNodeResult treeNode = new TreeNodeResult();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
			
			listTreeNode.add(treeNode);
		}
		return listTreeNode;
	}

}
