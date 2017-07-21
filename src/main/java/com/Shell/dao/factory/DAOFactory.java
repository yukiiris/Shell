package com.Shell.dao.factory;

import com.Shell.dao.ICommandDAO;
import com.Shell.dao.IKeyDAO;
import com.Shell.dao.IUserDAO;
import com.Shell.dao.proxy.CommandDAOProxy;
import com.Shell.dao.proxy.KeyDAOProxy;
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
}
