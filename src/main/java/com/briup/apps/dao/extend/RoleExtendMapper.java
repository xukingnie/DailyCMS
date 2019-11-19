package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.Role;
import com.briup.apps.bean.extend.RoleExtend;

public interface RoleExtendMapper {
	List<Role> selectByUserId(long id);

    List<RoleExtend> selectAll();
}
