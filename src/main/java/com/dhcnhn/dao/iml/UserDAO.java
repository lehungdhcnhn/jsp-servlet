package com.dhcnhn.dao.iml;

import java.util.List;

import com.dhcnhn.dao.IUserDAO;
import com.dhcnhn.mapper.UserMapper;
import com.dhcnhn.model.UserModel;

public class UserDAO  extends AbtractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("Select *from user AS u ");
		sql.append("INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" Where username=? And password=? And status=?");
		
		List<UserModel> user = query(sql.toString(),new UserMapper(), username,password,status);
		return user.isEmpty()?null : user.get(0);
	}

}
