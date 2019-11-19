package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.UserExtend;
import com.briup.apps.utils.CustomerException;

public interface IUserService {
	// 通过ID查询用户
	UserExtend findById(long id);

	// 查询所有用户
	List<User> findAll();

	// 级联查询所有用户
	List<UserExtend> cascadeFindAll();

	// 保存或更新
	void saveOrUpdate(User user) throws CustomerException;

	// 变化状态
	void changeStatus(long id, String status) throws CustomerException;

	// 通过ID删除用户
	void deleteById(long id) throws CustomerException;

	// 设置角色
	void setRoles(long id, List<Long> roles);
}
