package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@RequestMapping(value="/item")
@Controller
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable(value="itemId") long itemId){
		return itemService.getTbItem(itemId);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public EasyUIDateGridResult getItemList(@RequestParam(value="page",defaultValue="1")int pageNum, 
							  @RequestParam(value="rows",defaultValue="30")int pageSize){
		EasyUIDateGridResult result = itemService.getItemList(pageNum, pageSize);
		return  result;
	}

}
