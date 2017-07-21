package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Shell.dao.IGroupDAO;
import com.Shell.vo.Group;

public class GroupDAOImpl implements IGroupDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public GroupDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public  String getAll()
	{
		String sql = "SELECT * FROM groups";
		String key = "";
		try
		{
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				//key = rs.getString("secret_key");
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
	
	public boolean addGroup(Group group)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO groups(gid,name) VALUES(?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, group.getGid());
			pstm.setString(2, group.getName());
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
	
	public boolean findGroupById(int gid)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT * FROM group WHERE id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, gid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				isFind = true;
				//user.setID(rs.getInt(1));
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
		return isFind;
	}
}
