package com.Shell.dao;

import java.util.List;

import com.Shell.vo.Group;

public interface IGroupDAO {

	public boolean addGroup(Group group);
	public boolean findGroupById(int gid);
	//public boolean deleteGroup(Group group);
	//public List<Group>
}
