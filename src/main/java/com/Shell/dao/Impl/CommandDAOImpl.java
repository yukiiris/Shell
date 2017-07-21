package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.ICommandDAO;
import com.Shell.vo.Command;

public class CommandDAOImpl implements ICommandDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public CommandDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public Command getNext()
	{
		Command command = new Command();
		try
		{
			String sql = "select * from command order by date desc limit 1";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				command.setCid(rs.getInt("cid"));
				command.setCommand(rs.getString("command"));
				command.setDate(rs.getDate("date"));
				command.setStatus(rs.getInt("status"));
				command.setUid(rs.getInt("uid"));
			}
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return command;
	}
	
	public boolean addCommand(Command command)
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO command(cid,date,uid,command,status) VALUES(null,?,?,?,-1)";
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, (Date)command.getDate());
			pstm.setInt(2, command.getUid());
			pstm.setInt(3, command.getUid());
			
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

	public boolean ajustCommand(Command command)
	{
		boolean isCreate = false;
		try
		{
			String sql = "UPDATE command SET command=? where cid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(2, command.getCid());
			pstm.setString(1, command.getCommand());
			
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


	public List<Command> getAll(int uid)
	{
		List<Command> result = new ArrayList<>();
		
		try
		{
			String sql = "SELECT * FROM command WHERE uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				Command command = new Command();
				command.setCid(rs.getInt("cid"));
				command.setCommand(rs.getString("command"));
				command.setDate(rs.getDate("date"));
				command.setStatus(rs.getInt("status"));
				command.setUid(rs.getInt("uid"));
				result.add(command);
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
