package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IFileDAO;
import com.Shell.vo.File;

public class FileDAOImpl implements IFileDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public FileDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean changeGroup(int gid, String name)
	{
		boolean isChange = false;
		try
		{
			String sql = "UPDATE files SET gid=? WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			
			if (pstm.executeUpdate() > 0)
			{
				isChange = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
		return isChange;
	}
	
	public File findFileByName(String name)
	{
		File file = new File();
		try
		{
			String sql = "SELECT * FROM file WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				file.setGid(rs.getInt("gid"));
				file.setName(rs.getString("name"));
				file.setUid(rs.getInt("uid"));
			}
			rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
		return file;
	}
	
	public boolean addFiles(List<File> files)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO file(uid,name,gid) VALUES(?,?,?)";
			for (File file : files)
			{
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, file.getUid());
				pstm.setString(2, file.getName());
				pstm.setInt(3, file.getGid());
				
				if (pstm.executeUpdate() > 0)
				{
					isCreate = isCreate && true;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isCreate;
	}
	
	public boolean deleteFile(String name)
	{
		boolean isDelete = false;
		try
		{
			System.out.println(name);
			String sql = "DELETE FROM file WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
			}
			
			sql = "DELETE FROM ua WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = isDelete && true;
			}
			sql = "DELETE FROM ga WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete =  isDelete && true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isDelete;
	}
	
	public List<File> getAll()
	{
		String sql = "SELECT * FROM file";
		List<File> files = new ArrayList<>();
		try
		{
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				File file = new File();
				file.setGid(rs.getInt("gid"));
				file.setName(rs.getString("name"));
				file.setUid(rs.getInt("uid"));
				files.add(file);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
		return files;
	}
}
