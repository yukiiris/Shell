package com.Shell.Jersey.Impl;

import com.Shell.Jersey.API.UserAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.Token;
import com.Shell.vo.User;

public class UserImpl implements UserAPI{

	@Override
	public boolean createUser(User user)
	{
		boolean isCreate = false;
		try
		{
			isCreate = DAOFactory.getIUserDAOInstance().doCreate(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;
	}
	
	@Override
	public boolean deleteUser(User user)
	{
		
		boolean isCreate = false;
		try
		{
			//TODO
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

	public boolean setGroup(User user)
	{
		return false;
	}
}
