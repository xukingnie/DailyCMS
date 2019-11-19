package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Article;
import com.briup.apps.bean.extend.ArticleExtend;
import com.briup.apps.utils.CustomerException;

public interface IArticleSerivce {
	List<Article> findAll();
	
	List<ArticleExtend> cascadeFindAll();
	
	ArticleExtend findById(long id);
	
	void saveOrUpdate(Article article);
	
	void deleteById(long id)throws CustomerException;
}
