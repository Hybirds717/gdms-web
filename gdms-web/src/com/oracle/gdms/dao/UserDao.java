package com.oracle.gdms.dao;

import com.oracle.gdms.entity.UserModel;

public interface UserDao {
	public int add(UserModel user);

	public UserModel login(UserModel user);

	public int hasMobile(String mobile);
}
