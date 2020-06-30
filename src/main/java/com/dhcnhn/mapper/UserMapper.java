package com.dhcnhn.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dhcnhn.model.RoleModel;
import com.dhcnhn.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
		UserModel user = new UserModel();
		try {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("username"));
			user.setFullName(rs.getString("fullname"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getInt("status"));
			
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				user.setRole(role);
			}catch(Exception e) {
				System.out.print(e.getMessage());
			}
			return user;
			
		} catch (SQLException e) {
			
			return null;
		}
	}

}
