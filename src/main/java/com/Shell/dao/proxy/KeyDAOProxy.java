package com.Shell.dao.proxy;

import java.sql.SQLException;

import com.Shell.dao.IKeyDAO;
import com.Shell.dao.Impl.KeyDAOImpl;
import com.Shell.dbc.DatabaseConnection;

public class KeyDAOProxy implements IKeyDAO{
	
	private DatabaseConnection dbc = null;
	private IKeyDAO dao = null;
	
	public KeyDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new KeyDAOImpl(dbc.getConnection());
	}

	public boolean setKey(String key)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.setKey(key);
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
	
	public String getKey()
	{
		String key = "";
		try
		{
			key = dao.getKey();
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
		return key;
	}
	
	

}
