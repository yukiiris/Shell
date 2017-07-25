package com.Shell.dao;

import java.util.List;

import com.Shell.vo.Group;

public interface IGroupDAO {

	public boolean addGroup(Group group);
	public Group findGroupById(int gid);
	public boolean addUser(int uid, int gid, int isDe);
	public List<Group> getAllGroup();
	public String findAuthorityById(int gid, String authority);
	public boolean setAuthority(int gid, String authority, String file);
	public String findUser(int gid);
	public boolean deleteGroup(Group group);
}
