package com.briup.apps.bean.extend;

import java.util.List;

import com.briup.apps.bean.Role;
import com.briup.apps.bean.User;

/**
 * @description 用户拓展类
 * @author xuking
 *
 */
public class UserExtend extends User{
	public static final String STAUS_NORMAL = "正常";
	public static final String STAUS_FORBIDDEN = "禁用";
	
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
