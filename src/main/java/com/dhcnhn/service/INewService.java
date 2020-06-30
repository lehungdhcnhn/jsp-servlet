package com.dhcnhn.service;

import java.util.List;

import com.dhcnhn.model.NewModel;
import com.dhcnhn.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save (NewModel newModel);
	void delete(long[] ids);
	NewModel update(NewModel updateNew);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
	NewModel findOne(long id);
}
