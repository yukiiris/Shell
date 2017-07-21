package com.Shell.dao.proxy;

import java.sql.SQLException;

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
	
	public boolean findGroupById(int gid)
	{
		boolean isFind = false;
		try
		{
			isFind = dao.findGroupById(gid);
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
