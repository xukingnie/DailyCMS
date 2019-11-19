package com.briup.apps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.bean.Category;
import com.briup.apps.bean.CategoryExample;
import com.briup.apps.dao.CategoryMapper;
import com.briup.apps.service.ICategoryService;
import com.briup.apps.utils.CustomerException;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void saveOrUpdate(Category category) throws Exception {
		if (category.getId() != null) {
			categoryMapper.updateByPrimaryKey(category);
		} else {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if (list.size() > 0) {
				throw new Exception("栏目不可用重名");
			}
			categoryMapper.insert(category);
		}
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category == null) {
			throw new CustomerException("栏目不存在");
		}
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws CustomerException {
		for(long id : ids) {
			this.deleteById(id);
		}
	}

}
