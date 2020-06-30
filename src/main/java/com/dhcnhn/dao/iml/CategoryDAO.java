package com.dhcnhn.dao.iml;

import java.util.List;
import com.dhcnhn.dao.ICategoryDAO;
import com.dhcnhn.mapper.CategoryMapper;
import com.dhcnhn.model.CategoryModel;

public class CategoryDAO extends AbtractDAO<CategoryModel> implements ICategoryDAO {
	public List<CategoryModel> findAll() {
		String sql = "SELECT *FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql ="SELECT *FROM category WHERE id=?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty()?null :category.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql ="SELECT *FROM category WHERE code=?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty()?null:category.get(0);
	}
}
