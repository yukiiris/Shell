package com.Shell.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IDataDAO;
import com.Shell.dao.Impl.DataDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.Data;

public class DataDAOProxy implements IDataDAO{

	private DatabaseConnection dbc = null;
	private DataDAOImpl dao = null;
	
	public DataDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new DataDAOImpl(dbc.getConnection());
	}
	
	public boolean addData(float cpu, float mem, float net)
	{
		boolean isAdd = false;
		try
		{
			isAdd = dao.addData(cpu, mem, net);
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
		return isAdd;
	}
	
	public List<Data> getData()
	{
		List<Data> datas = new ArrayList<>();
		try
		{
			datas = dao.getData();
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
		return datas;
	}
}
