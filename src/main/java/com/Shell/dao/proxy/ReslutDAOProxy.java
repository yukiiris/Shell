package com.Shell.dao.proxy;

import java.sql.SQLException;

import com.Shell.dao.IResultDAO;
import com.Shell.dao.Impl.ResultDAOImpl;
import com.Shell.dbc.DatabaseConnection;

public class ReslutDAOProxy implements IResultDAO{

	private DatabaseConnection dbc = null;
	private IResultDAO dao = null;
	
	public ReslutDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new ResultDAOImpl(dbc.getConnection());
	}
	
	
	public boolean addResult(int cid, String result)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.addResult(cid, result);
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
	
	public String findResult(int cid)
	{
		String result = null;
		try
		{
			result = dao.findResult(cid);
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
}
