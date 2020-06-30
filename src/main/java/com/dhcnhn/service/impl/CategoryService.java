package com.dhcnhn.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dhcnhn.dao.ICategoryDAO;
import com.dhcnhn.model.CategoryModel;
import com.dhcnhn.service.ICategoryService;

public class CategoryService implements ICategoryService {
	@Inject
	private ICategoryDAO categoryDAO;
	@Override
	public List<CategoryModel> findAll() {

		return categoryDAO.findAll();
	}
	@Override
	public CategoryModel findOne(long id) {
		return categoryDAO.findOne(id);
	}

}
