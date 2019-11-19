package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Article;
import com.briup.apps.bean.extend.ArticleExtend;
import com.briup.apps.service.IArticleSerivce;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private IArticleSerivce articleService;

	@GetMapping("findAll")
	public Message findAll() {
		List<Article> findAll = articleService.findAll();
		return MessageUtil.success("200", findAll);
	}

	@GetMapping("cascadeFindAll")
	public Message cascadeFindAll() {
		List<ArticleExtend> cascadeFindAll = articleService.cascadeFindAll();
		return MessageUtil.success("200", cascadeFindAll);
	}

	@GetMapping("findById")
	public ArticleExtend findById(long id) {
		return articleService.findById(id);
	}

	@ApiOperation(value = "保存或更新")
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(Article article) {
		articleService.saveOrUpdate(article);
		return "update success";
	}

	@ApiOperation(value = "删除")
	@ApiImplicitParams(@ApiImplicitParam(name = "id", value = "编号", paramType = "query", required = true))
	@GetMapping("deleteById")
	public Message deleteById(@RequestParam("id") long id) throws Exception {
		articleService.deleteById(id);
		return MessageUtil.success("delete success");
	}
}
