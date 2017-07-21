package com.Shell.dao;

import com.Shell.vo.User;

public interface IUserDAO {

	public boolean doCreate(User user);
	public boolean findUser(User user);
	public boolean findUserByName(String name);
	public boolean changePassword(User user);
	public boolean setGroup();
}
