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
}
