package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.extend.UserExtend;

public interface UserExtendMapper {
	UserExtend selectById(long id);
	
	List<UserExtend> selectAll();
}
