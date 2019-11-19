package com.briup.apps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.User;
import com.briup.apps.bean.UserExample;
import com.briup.apps.bean.UserRole;
import com.briup.apps.bean.UserRoleExample;
import com.briup.apps.bean.extend.UserExtend;
import com.briup.apps.dao.UserMapper;
import com.briup.apps.dao.UserRoleMapper;
import com.briup.apps.dao.extend.UserExtendMapper;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.CustomerException;

@SuppressWarnings("unused")
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;

	@Resource
	private UserExtendMapper userExtendMapper;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public UserExtend findById(long id) {
		return userExtendMapper.selectById(id);
	}

	@Override
	public List<User> findAll() {
		return userMapper.selectByExample(new UserExample());
	}

	@Override
	public List<UserExtend> cascadeFindAll() {
		return userExtendMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(User user) throws CustomerException {
		if (user.getId() != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(user.getUsername());
			List<User> list = userMapper.selectByExample(example);
			if (list.size() > 0) {
				throw new CustomerException("该用户已经被占用");
			}
			// init
			user.setRegisterTime(new Date().getTime());
			user.setStatus(UserExtend.STAUS_NORMAL);
			userMapper.insert(user);
		}
	}

	@Override
	public void changeStatus(long id, String status) throws CustomerException {
		User user = this.findById(id);
		if (user == null) {
			throw new CustomerException("该用户不存在");
		}
		
		user.setStatus(status);
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		User user = this.findById(id);
		if (user == null) {
			throw new CustomerException("该用户不存在");
		}
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void setRoles(long id, List<Long> roles) {
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);

		List<UserRole> list = userRoleMapper.selectByExample(example);
		List<Long> oldRoles = new ArrayList<>();
		Iterator<UserRole> iterator = list.iterator();
		while (iterator.hasNext()) {
			oldRoles.add(iterator.next().getRoleId());
		}
		// [1,2,3] -> [3,4] 添加1,2 => [1,2,3,4]
		// 依次判断新角色是否存在于list中，如果不存在则添加
		for (Long roleId : roles) {
			if (!oldRoles.contains(roleId)) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(id);
				userRoleMapper.insert(userRole);
			}
		}
		// [1,2,3] -> [1,2,3,4] 删除 3,4 => [1,2]
		// 依次判断老的角色是否存在于roles中，如果不存在则删除
		for (UserRole userRole : list) {
			if (!roles.contains(userRole.getRoleId())) {

				UserRoleExample example1 = new UserRoleExample();
				userRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}
	}

}
