package com.dhcnhn.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.dhcnhn.dao.ICategoryDAO;
import com.dhcnhn.dao.INewDAO;
import com.dhcnhn.model.CategoryModel;
import com.dhcnhn.model.NewModel;
import com.dhcnhn.paging.Pageble;
import com.dhcnhn.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDAO newDao;
	@Inject
	private ICategoryDAO categoryDao;
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		
		return newDao.findByCategoryID(categoryId);
	}

	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category =categoryDao.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		Long newId = newDao.save(newModel);
		return newDao.findOne(newId);
	}

	@Override
	public void delete(long []ids) {
		//newDao.delete(id);
		for(long id: ids)
		{
			newDao.delete(id);
		}
		
	}

	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDao.findOne(updateNew.getId());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category= categoryDao.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(category.getId());
		newDao.update(updateNew);
		return newDao.findOne(updateNew.getId());
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDao.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel newModel = newDao.findOne(id);
		CategoryModel categoryModel = categoryDao.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
		return newModel;
	}

	
}
