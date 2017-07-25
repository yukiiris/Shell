package com.Shell.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IFileDAO;
import com.Shell.dao.Impl.FileDAOImpl;
import com.Shell.dbc.DatabaseConnection;
import com.Shell.vo.File;

public class FileDAOProxy implements IFileDAO{

	private DatabaseConnection dbc = null;
	private FileDAOImpl dao = null;
	
	public FileDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new FileDAOImpl(dbc.getConnection());
	}
	

	public boolean addFiles(List<File> files)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.addFiles(files);
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
	
	
	public File findFileByName(String name)
	{
		File file = new File();
		try
		{
			file = dao.findFileByName(name);
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
		return file;
	}



	@Override
	public List<File> getAll() 
	{
		List<File> files = new ArrayList<>();
		
		try
		{
			files = dao.getAll();
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
		return files;
	}
	
	public boolean deleteFile(String name)
	{
		boolean isDelete = false;
		try
		{
			isDelete = dao.deleteFile(name);
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
		return isDelete;
	}


	@Override
	public boolean changeGroup(int gid, String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
