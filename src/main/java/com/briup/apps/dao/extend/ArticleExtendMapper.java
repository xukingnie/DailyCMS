package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.extend.ArticleExtend;

public interface ArticleExtendMapper {
	//	查询所有文章
	List<ArticleExtend> selectAll();
	//	通过Id查询(含评论'comment')
	ArticleExtend selectById(long id);
	
}
