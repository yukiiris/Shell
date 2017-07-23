package com.Shell.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IUserDAO;
import com.Shell.dao.Impl.UserDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.File;
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
	
	public boolean setAuthority(List<Integer> users, List<String> authorities, List<File> files)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.setAuthority(users, authorities, files);
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
	
	public String findAuthorityByName(String name, String Authotiry)
	{
		String result = null;
		try
		{
			result = dao.findAuthorityByName(name, Authotiry);
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
		return result;
	}
	
	public String findAuthorityById(int uid, String Authotiry)
	{
		String result = null;
		try
		{
			result = dao.findAuthorityById(uid, Authotiry);
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
		return result;
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

	public User findUserById(int uid) 
	{
		User user = new User();
		try
		{
			user = dao.findUserById(uid);
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
		return user;
	}
	
	@Override
	public List<Integer> getAllId() {
		List<Integer> id = new ArrayList<>();
		try 
		{
			id = dao.getAllId();
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
		return id;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		try 
		{
			users = dao.getAllUser();
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
		return users;
	}

}
