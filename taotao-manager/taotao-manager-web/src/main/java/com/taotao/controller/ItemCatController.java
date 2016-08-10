package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TreeNodeResult;
import com.taotao.service.ItemCatService;

@RequestMapping(value="/item/cat")
@Controller
public class ItemCatController {
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<TreeNodeResult> list(@RequestParam(value="id",defaultValue="0")long parentId){
		List<TreeNodeResult> itemCatList = itemCatService.getItemCatByParentId(parentId);
		System.out.println(itemCatList);
		return itemCatList;
	}

}
