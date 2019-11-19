package com.briup.apps.bean.extend;

import java.util.List;

import com.briup.apps.bean.Privilege;
import com.briup.apps.bean.Role;

/**
 * @description 角色拓展类
 * @author xuking
 *
 */
public class RoleExtend extends Role {
	private List<Privilege> pricileges;

	public List<Privilege> getPricileges() {
		return pricileges;
	}

	public void setPricileges(List<Privilege> pricileges) {
		this.pricileges = pricileges;
	}
}
