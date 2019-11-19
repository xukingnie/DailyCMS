package com.briup.apps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.Article;
import com.briup.apps.bean.ArticleExample;
import com.briup.apps.bean.extend.ArticleExtend;
import com.briup.apps.dao.ArticleMapper;
import com.briup.apps.dao.extend.ArticleExtendMapper;
import com.briup.apps.service.IArticleSerivce;
import com.briup.apps.utils.CustomerException;

@Service
public class ArticleServiceImpl implements IArticleSerivce {
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private ArticleExtendMapper articleExtendMapper;

	@Override
	public List<Article> findAll() {
		return articleMapper.selectByExample(new ArticleExample());
	}

	// 保存或更新
	@Override
	public void saveOrUpdate(Article article) {
		if (article.getId() != null) {
			articleMapper.updateByPrimaryKey(article);
		} else {
			// 初始化
			article.setStatus(ArticleExtend.STATUS_UNCHECK);
			article.setThumpUp(0L);
			article.setThumpDown(0L);
			article.setPublishTime(new Date().getTime());
			article.setReadTimes(0L);
			// 注意Bolb文件的类型重写
			articleMapper.insert(article);
		}
	}

	@Override
	public List<ArticleExtend> cascadeFindAll() {
		return articleExtendMapper.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleExtendMapper.selectById(id);
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);
		if (article.getId() == null) {
			throw new CustomerException("id is error");
		} else {
			articleMapper.deleteByPrimaryKey(id);
		}
	}

}
