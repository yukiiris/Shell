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
	
	
	public boolean setGroup(User user, int gid)
	{
		boolean isDelete = false;
		
		try
		{
			String sql = "UPDATE user_to_group SET degroup=0 WHERE degroup=1";
			pstm = conn.prepareStatement(sql);
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
			}
			
			sql = "UPDATE user_to_group SET degroup=1 WHERE uid=? AND gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUid());
			pstm.setInt(2, gid);
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
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
		return isDelete;
	}
	
	public boolean deleteUser(User user)
	{
		boolean isDelete = false;
	
		try
		{
			String sql = "DELETE FROM user WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUid());
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
			}
			sql = "DELETE FROM ua WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUid());
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = isDelete && true;
			}
			sql = "DELETE FROM user_to_group WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUid());
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = isDelete && true;
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
		return isDelete;
	}
	
	public int findGidByUid(int uid)
	{
		int gid = 0;
		try
		{
			String sql = "SELECT gid FROM user_to_group WHERE uid=? and degroup=1";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				gid = rs.getInt("gid");
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
		return gid;
	}
	
	public String findAuthorityById(int uid, String file)
	{
		String result = null;
		try
		{
			String sql = "select authority from ua where uid=? and name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			pstm.setString(2, file);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				result = rs.getString("authority");
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
	
//	public String findAuthorityByName(String name, String file)
//	{
//		String result = null;
//		try
//		{
//			String sql = "select authority from authority where uid=? and file=?";
//			pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, name);
//			pstm.setString(2, file);
//			ResultSet rs = pstm.executeQuery();
//			
//			while (rs.next())
//			{
//				result = rs.getString("authority");
//			}
//			rs.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if (pstm != null)
//				{
//					pstm.close();
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//			try
//			{
//				if (conn != null)
//				{
//					conn.close();
//				}
//			}
//			catch (Exception exception)
//			{
//				exception.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	public User findUserById(int uid)
	{
		User user = new User();
		try
		{
			String sql = "SELECT * FROM user WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
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
	
	public int doCreate(User user)
	{
		int uid = 0;
		try
		{
			String sql = "INSERT INTO user(uid,name,password) VALUES(null,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword().hashCode() + "");
			
			if (pstm.executeUpdate() > 0)
			{
				uid = findID(user.getName());
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
		return uid;
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
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
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
	
	public boolean setAuthority(int uid, String authorities, List<File> files)
	{
		boolean isCreate = false;
		
		for (File file : files)
		{
				try
				{
					String sql = "DELETE FROM ua WHERE uid=? AND name=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, uid);
					pstm.setString(2, file.getName());
					pstm.executeUpdate();
					
					sql = "INSERT INTO ua(uid, authority, name) VALUES(?,?,?)";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, uid);
					pstm.setString(2, authorities);
					pstm.setString(3, file.getName());
					
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
			String sql = "SELECT uid FROM user WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				ID = rs.getInt("uid");
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
