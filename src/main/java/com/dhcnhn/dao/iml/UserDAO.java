package com.dhcnhn.dao.iml;

import java.util.List;

import com.dhcnhn.dao.IUserDAO;
import com.dhcnhn.mapper.UserMapper;
import com.dhcnhn.model.UserModel;

public class UserDAO  extends AbtractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String Password, Integer Status) {
		StringBuilder sql = new StringBuilder("Select *from user AS u ");
		sql.append("INNER JOIN role AS r ON r.id = u.roleid");
		sql.append("Where userName=? And Password=? And Status=?");
		
		List<UserModel> user = query(sql.toString(),new UserMapper(), userName,Password,Status);
		return user.isEmpty()?null : user.get(0);
	}

}
