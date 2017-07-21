package com.Shell.dao.proxy;

import java.sql.SQLException;

import com.Shell.dao.IUserDAO;
import com.Shell.dao.Impl.UserDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.User;


public class UserDAOProxy implements IUserDAO{

	private DatabaseConnection dbc = null;
	private IUserDAO dao = null;
	
	public UserDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new UserDAOImpl(dbc.getConnection());
	}
	
	public boolean changePassword(User user)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.changePassword(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isCreate;
	}
	
	public boolean doCreate(User user)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.doCreate(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isCreate;
	}
	
	public boolean findUser(User user)
	{
		boolean isFind = false;
		try
		{
			isFind = dao.findUser(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isFind;
	}

	@Override
	public boolean findUserByName(String name) 
	{
		boolean isFind = false;
		try
		{
			isFind = dao.findUserByName(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isFind;
	}

}
