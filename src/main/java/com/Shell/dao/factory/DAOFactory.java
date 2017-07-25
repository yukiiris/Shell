package com.Shell.dao.factory;

import com.Shell.dao.ICommandDAO;
import com.Shell.dao.IDataDAO;
import com.Shell.dao.IFileDAO;
import com.Shell.dao.IGroupDAO;
import com.Shell.dao.IKeyDAO;
import com.Shell.dao.IResultDAO;
import com.Shell.dao.IUserDAO;
import com.Shell.dao.proxy.CommandDAOProxy;
import com.Shell.dao.proxy.DataDAOProxy;
import com.Shell.dao.proxy.FileDAOProxy;
import com.Shell.dao.proxy.GroupDAOProxy;
import com.Shell.dao.proxy.KeyDAOProxy;
import com.Shell.dao.proxy.ReslutDAOProxy;
import com.Shell.dao.proxy.UserDAOProxy;

public class DAOFactory {
	
	public static IUserDAO getIUserDAOInstance() throws Exception
	{
			return new UserDAOProxy();
	}
	
	public static IKeyDAO getIKeyDAOInstance() throws Exception
	{
			return new KeyDAOProxy();
	}
	
	public static ICommandDAO getICommandDAOInstance() throws Exception
	{
			return new CommandDAOProxy();
	}
	
	public static IResultDAO getIResultDAOInstance() throws Exception
	{
			return new ReslutDAOProxy();
	}
	
	public static IGroupDAO getIGroupDAOInstance() throws Exception
	{
			return new GroupDAOProxy();
	}
	
	public static IFileDAO getIFileDAOInstance() throws Exception
	{
			return new FileDAOProxy();
	}
	
	public static IDataDAO getIDataDAOInstance() throws Exception
	{
			return new DataDAOProxy();
	}
}
