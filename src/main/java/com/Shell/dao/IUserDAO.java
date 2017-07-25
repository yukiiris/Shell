package com.Shell.dao;

import java.util.List;

import com.Shell.vo.File;
import com.Shell.vo.User;

public interface IUserDAO {

	public int doCreate(User user);
	public boolean findUser(User user);
	public boolean findUserByName(String name);
	public User findUserById(int uid);
	public boolean changePassword(User user);
	public String findAuthorityById(int uid, String file);
	public boolean setAuthority(int uid, String authorities, List<File> files);
	public List<Integer> getAllId();
	public List<User> getAllUser();
	public boolean deleteUser(User user);
	public int findGidByUid(int uid);
	public boolean setGroup(User user, int uid);
}
