package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Category;
import com.briup.apps.service.ICategoryService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("栏目相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("findAll")
	public Message findAll() {
		List<Category> list = categoryService.findAll();
		return MessageUtil.success("20000", list);
	}

	@ApiOperation(value = "保存或更新" ,notes="保存时无需id传入")
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(Category category) throws Exception {
		categoryService.saveOrUpdate(category);
		return MessageUtil.success("update success");
	}

	@ApiOperation(value = "删除")
	@GetMapping("deleteById")
	public Message deleteById(@RequestParam("id") long id) throws Exception {
		categoryService.deleteById(id);
		return MessageUtil.success("delete success");
	}

	@ApiOperation(value = "批量删除")
	@PostMapping(value = "batchDelete")
	public Message batchDelete(@RequestBody long[] ids) throws Exception {
		categoryService.batchDelete(ids);
		return MessageUtil.success("batch delete success");
	}
}
