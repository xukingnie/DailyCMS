package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.Comment;

public interface CommentExtendMapper {
	List<Comment> selectByArticleId(long article_id);
}
