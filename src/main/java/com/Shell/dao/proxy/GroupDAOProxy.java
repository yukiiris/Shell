package com.Shell.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IGroupDAO;
import com.Shell.dao.Impl.GroupDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.Group;

public class GroupDAOProxy implements IGroupDAO{

	private DatabaseConnection dbc = null;
	private GroupDAOImpl dao = null;
	
	public GroupDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new GroupDAOImpl(dbc.getConnection());
	}
	
	public String findAuthorityById(int gid, String Authotiry)
	{
		String result = null;
		try
		{
			result = dao.findAuthorityById(gid, Authotiry);
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
	
	public boolean addGroup(Group group)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.addGroup(group);
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
	
	public boolean addUser(int uid, int gid)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.addUser(uid, gid);
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
	
	public Group findGroupById(int gid)
	{
		Group group = new Group();
		try
		{
			group = dao.findGroupById(gid);
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
		return group;
	}



	@Override
	public List<Group> getAllGroup() 
	{
		List<Group> groups = new ArrayList<>();
		
		try
		{
			groups = dao.getAllGroup();
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
		return groups;
	}

	@Override
	public boolean setAuthority(int gid, List<String> authority, String file)
	{
		boolean isSet = false;
		try
		{
			isSet = dao.setAuthority(gid, authority, file);
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
		return isSet;
	}

}
