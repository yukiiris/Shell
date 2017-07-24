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

public class GroupDAOImpl implements IGroupDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public GroupDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean setAuthority(int gid, List<String> authority, String file)
	{
		boolean isCreate = false;
		
		for (String st : authority)
		{
			try
				{
					String sql = "UPDATE groups SET ?=? where gid=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(3, gid);
					String s = DAOFactory.getIUserDAOInstance().findAuthorityById(gid, st);
					pstm.setString(2, st + " " + file);
					pstm.setString(1, s);
					
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

	public String findAuthorityById(int gid, String authority)
	{
		String result = null;
		try
		{
			String sql = "SELECT ? FROM groups WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, authority);
			pstm.setInt(2, gid);
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
				group.setUsers(rs.getString("users"));
				group.setR(rs.getString("r"));
				group.setW(rs.getString("w"));
				group.setX(rs.getString("x"));
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
	
	public boolean addUser(int uid, int gid)
	{
		boolean isCreate = false;
		try
		{
			String sql = "UPDATE TABLE group SET users=? WHERE gid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, uid + " ");
			pstm.setInt(2, gid);
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
				group.setUsers(rs.getString("users"));
				group.setR(rs.getString("r"));
				group.setW(rs.getString("w"));
				group.setX(rs.getString("x"));
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
