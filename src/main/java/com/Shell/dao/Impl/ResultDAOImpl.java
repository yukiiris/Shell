package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Shell.dao.IResultDAO;

public class ResultDAOImpl implements IResultDAO{
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public ResultDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public String findResult(int cid)
	{
		String result = null;
		try
		{
			String sql = "SELECT result FROM user WHERE cid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				
				result = rs.getString("result");
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
		return result;
	}
	
	public boolean addResult(int cid, String result)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO result(cid,result) VALUES(?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cid);
			pstm.setString(2, result);
			
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
