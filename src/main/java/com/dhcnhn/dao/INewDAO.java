package com.dhcnhn.dao;

import java.util.List;

import com.dhcnhn.model.NewModel;
import com.dhcnhn.paging.Pageble;

public interface INewDAO extends IGenericDAO<NewModel>{
	List<NewModel> findAll(Pageble pageble);
	NewModel findOne(Long id);
	List<NewModel> findByCategoryID(Long categoryId); 
	Long save(NewModel newModel);
	void delete(Long id);
	void update(NewModel updateNew);
	int getTotalItem();
}
