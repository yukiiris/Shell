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

}
