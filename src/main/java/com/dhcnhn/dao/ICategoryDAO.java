package com.dhcnhn.dao;

import java.util.List;

import com.dhcnhn.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {
	List<CategoryModel> findAll();
}
