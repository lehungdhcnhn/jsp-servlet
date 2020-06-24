package com.dhcnhn.service;

import java.util.List;

import com.dhcnhn.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
}