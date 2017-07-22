package com.Shell.Jersey.Impl;

import com.Shell.Jersey.API.GroupAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.Group;

public class GroupImpl implements GroupAPI{

	@Override
	public boolean addGroup(Group group) {
		boolean isAdd = false;
		try {
			isAdd = DAOFactory.getIGroupDAOInstance().addGroup(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdd;
	}

	@Override
	public boolean deleteGroup(Group group) {
		boolean isDelete = false;
		try {
			//isDelete = DAOFactory.getIGroupDAOInstance().d
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDelete;
	}

	@Override
	public boolean addUser(int uid, int gid) {
		boolean isAdd = false;
		try {
			isAdd = DAOFactory.getIGroupDAOInstance().addUser(uid, gid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdd;
	}

	
}
