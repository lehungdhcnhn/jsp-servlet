package com.dhcnhn.dao;

import com.dhcnhn.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel>{
	UserModel findByUserNameAndPasswordAndStatus(String username, String password, Integer status);
}
