package com.Shell.Jersey.Impl;

import com.Shell.Jersey.API.UserAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.Token;
import com.Shell.vo.User;

public class UserImpl implements UserAPI{

	@Override
	public int createUser(User user, int gid)
	{
		int uid = 0;
		if (gid == 0)
		{
			return 0;
		}
		try
		{
			uid = DAOFactory.getIUserDAOInstance().doCreate(user);
			GroupImpl groupImpl = new GroupImpl();
			groupImpl.addUser(uid, gid, 1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return uid;
	}
	
	@Override
	public boolean deleteUser(User user)
	{
		
		boolean isCreate = false;
		try
		{
			DAOFactory.getIUserDAOInstance().deleteUser(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;	
	}
	
	@Override
	public boolean changePassword(User user)
	{
		
		boolean isCreate = false;
		try
		{
			isCreate = DAOFactory.getIUserDAOInstance().changePassword(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;	
	}

	@Override
	public String login(User user) 
	{
		return Token.authenticateUser(user);
	}

	public boolean setGroup(User user, int gid)
	{
		boolean isSet = false;
		try {
			isSet = DAOFactory.getIUserDAOInstance().setGroup(user, gid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSet;
	}
}
