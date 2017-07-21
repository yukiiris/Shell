package com.Shell.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.ICommandDAO;
import com.Shell.dao.Impl.CommandDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.Command;

public class CommandDAOProxy implements ICommandDAO{

	private DatabaseConnection dbc = null;
	private CommandDAOImpl dao = null;
	
	public CommandDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new CommandDAOImpl(dbc.getConnection());
	}
	
	public Command getNext()
	{
		Command command = new Command();
		try
		{
			command = dao.getNext();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return command;
	}
	
	public boolean ajustCommand(Command command)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.ajustCommand(command);
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
	
	public boolean addCommand(Command command)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.addCommand(command);
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
	
	public List<Command> getAll(int uid)
	{
		List<Command> result = new ArrayList<>();
		try
		{
			result = dao.getAll(uid);
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
