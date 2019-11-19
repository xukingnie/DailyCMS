package com.briup.apps.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyTest {
	
	@GetMapping("/findAll")
	public List<String> findAll(){
		List<String> list = new ArrayList<>();
		list.add("first");
		list.add("second");
		return list ;
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(String msg) {
		return "success";
	}
}
