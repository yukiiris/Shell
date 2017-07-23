package com.Shell.dao;

import java.util.List;

import com.Shell.vo.File;
import com.Shell.vo.User;

public interface IUserDAO {

	public boolean doCreate(User user);
	public boolean findUser(User user);
	public boolean findUserByName(String name);
	public User findUserById(int uid);
	public boolean changePassword(User user);
	public String findAuthorityByName(String name, String authority);
	public String findAuthorityById(int uid, String authority);
	public boolean setAuthority(List<Integer> users, List<String> authorities, List<File> files);
	public List<Integer> getAllId();
	public List<User> getAllUser();
	//public boolean setGroup();
}
