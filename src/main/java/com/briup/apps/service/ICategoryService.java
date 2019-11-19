package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Category;
import com.briup.apps.utils.CustomerException;

public interface ICategoryService {
	List<Category> findAll();
	
	void saveOrUpdate(Category category) throws Exception;
	
	void deleteById(long id) throws CustomerException;
	
	void batchDelete(long[] ids) throws CustomerException;
}
