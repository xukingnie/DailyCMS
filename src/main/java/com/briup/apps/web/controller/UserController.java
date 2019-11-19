package com.briup.apps.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.extend.UserExtend;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;
import com.briup.apps.vm.UserVM;

import io.swagger.annotations.ApiOperation;

/**
 * <br>
 * 用户管理
 * 
 * @author xuking
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@ApiOperation("登录")
	@PostMapping(value="login")
	public Message login(@RequestBody UserVM userVM) {
		// 验证用户身份
		// 产生一个token，并缓存，后返回
		Map<String, String> map = new HashMap<>();
		map.put("token", "admin-token");
		return MessageUtil.success(map);

	}

	@ApiOperation(value = "退出")
	@PostMapping(value = "logout")
	public Message logout() {
		// 1. 将缓存中token移除
		// 2. 其他
		return MessageUtil.success("success");
	}

	@ApiOperation(value = "通过token获取用户信息")
	@GetMapping(value = "info")
	public Message info(String token) {
		// 1. 通过token获取用户信息
		UserExtend user = userService.findById(1l);
		return MessageUtil.success(user);
	}
}
