package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IGroupDAO;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.File;
import com.Shell.vo.Group;
import com.Shell.vo.User;

public class GroupDAOImpl implements IGroupDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public GroupDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean setAuthority(int gid, String authority, String file)
	{
		boolean isCreate = false;
		
			try
				{
					String sql = "DELETE FROM ga WHERE gid=? AND name=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, gid);
					pstm.setString(2, file);
					pstm.executeUpdate();
					
					sql = "INSERT INTO ga(gid,name,authority) VALUES(?,?,?)";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, gid);
					pstm.setString(2, file);
					pstm.setString(3, authority);
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

	public String findAuthorityById(int gid, String file)
	{
		String result = null;
		try
		{
			String sql = "select authority feom ga where gid=? and name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(2, file);
			pstm.setInt(1, gid);
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
	
	public List<Group> getAllGroup()
	{
		String sql = "SELECT * FROM groups";
		List<Group> groups = new ArrayList<>();
		try
		{
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				Group group = new Group();
				group.setGid(rs.getInt("gid"));
				group.setName(rs.getString("name"));
				groups.add(group);
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
		return groups;
	}
	
	public String findUser(int gid)
	{
		String user = null;
		try
		{
			String sql = "select users from groups where gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, gid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				user = rs.getString("users");
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
		return user;
	}
	public boolean deleteGroup(Group group)
	{
		boolean isDelete = false;
	
		try
		{
			String sql = "DELETE FROM groups WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, group.getGid());
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
			}
			sql = "DELETE FROM ga WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, group.getGid());
			
			if (pstm.executeUpdate() > 0)
			{
				isDelete = true;
			}
			sql = "DELETE FROM user_to_group WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, group.getGid());
			
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
	
	public boolean addUser(int uid, int gid, int isDe)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO user_to_group VALUES(?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(2, gid);
			pstm.setInt(1, uid);
			pstm.setInt(3, isDe);
			
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
	
	public Group findGroupById(int gid)
	{
		Group group = new Group();
		try
		{
			String sql = "SELECT * FROM groups WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, gid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				group.setGid(rs.getInt("gid"));
				group.setName(rs.getString("name"));
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
		return group;
	}
}
