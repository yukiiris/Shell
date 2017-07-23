package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.IGroupDAO;
import com.Shell.vo.Group;

public class GroupDAOImpl implements IGroupDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public GroupDAOImpl(Connection conn)
	{
		this.conn = conn;
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
			String sql = "SELECT * FROM group WHERE id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, gid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				group.setGid(rs.getInt("gid"));
				group.setName(rs.getString("name"));
				group.setUsers(rs.getString("users"));
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
