package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Shell.dao.IKeyDAO;

public class KeyDAOImpl implements IKeyDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public KeyDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public String getKey()
	{
		String sql = "SELECT * FROM mykey";
		String key = "";
		try
		{
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				key = rs.getString("secret_key");
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
		return key;
	}
	
	public boolean setKey(String key)
	{
		boolean isCreate = false;
		try
		{
			String sql = "UPDATE mykey SET secret_key=? WHERE id=1";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, key);
			
			if (pstm.executeUpdate() > 0)
			{
				isCreate = true;
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

}
