package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Shell.dao.IUserDAO;
import com.Shell.vo.User;

public class UserDAOImpl implements IUserDAO{
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public UserDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean findUserByName(String name)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT id FROM user WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
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
	
	public boolean doCreate(User user)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO user(id,name,group_name,password) VALUES(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getGroup());
			pstm.setString(3, user.getPassword().hashCode() + "");
			
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

	public boolean changePassword(User user)
	{
		boolean isCreate = false;
		try
		{
			String sql = "UPDATE user SET password=? where name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(2, user.getName());
			pstm.setString(1, user.getPassword().hashCode() + "");
			
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


	public boolean findUser(User user)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT id FROM user WHERE (name=? and password=?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword().hashCode() + "");
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

	public int findID(String name)
	{
		int ID = 0;
		try
		{
			String sql = "SELECT id FROM user WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				ID = rs.getInt("id");
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
		return ID;
	}
}
