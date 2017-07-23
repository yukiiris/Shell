package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IUserDAO;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.File;
import com.Shell.vo.User;

public class UserDAOImpl implements IUserDAO{
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public UserDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public String findAuthorityById(int uid, String authority)
	{
		String result = null;
		try
		{
			String sql = "SELECT ? FROM user WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, authority);
			pstm.setInt(2, uid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				result = rs.getString(authority);
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
	
	public String findAuthorityByName(String name, String authority)
	{
		String result = null;
		try
		{
			String sql = "SELECT ? FROM user WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, authority);
			pstm.setString(2, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				result = rs.getString(authority);
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
	
	public User findUserById(int uid)
	{
		User user = new User();
		System.out.println(11111);
		try
		{
			String sql = "SELECT * FROM user WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				user.setX(rs.getString("x"));
				user.setGroup(rs.getString("gid"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setR(rs.getString("r"));
				user.setW(rs.getString("w"));
				user.setUid(rs.getInt("uid"));
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
		return user;
	}
	
	public boolean findUserByName(String name)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT * FROM user WHERE name=?";
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

	public List<Integer> getAllId()
	{
		List<User> users = new ArrayList<>();
		List<Integer> id = new ArrayList<>();
		try
		{
			users = DAOFactory.getIUserDAOInstance().getAllUser();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for (User user : users)
		{
			id.add(user.getUid());
		}
		return id;
	}
	
	public List<User> getAllUser()
	{
		List<User> users = new ArrayList<>();
		try
		{
			String sql = "SELECT * FROM user";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				User user = new User();
				user.setX(rs.getString("x"));
				user.setGroup(rs.getString("gid"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setR(rs.getString("r"));
				user.setW(rs.getString("w"));
				user.setUid(rs.getInt("uid"));
				users.add(user);
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
		return users;
	}
	
	public boolean setAuthority(List<Integer> users, List<String> authorities, List<File> files)
	{
		boolean isCreate = false;
		
		for (Integer user : users)
		{
			for (File file : files)
			{
				for (String authority : authorities)
				{
					try
					{
						String sql = "UPDATE user SET ?=? where uid=?";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(3, user);
						String s = DAOFactory.getIUserDAOInstance().findAuthorityById(user, authority);
						pstm.setString(2, s + " " + file);
						pstm.setString(1, authority);
						
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
				}
			}
		}
		return isCreate;
	}

	public boolean findUser(User user)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT * FROM user WHERE (name=? and password=?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword().hashCode() + "");
			System.out.println(user.getPassword().hashCode());
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
