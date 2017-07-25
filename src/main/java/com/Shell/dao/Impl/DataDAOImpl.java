package com.Shell.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.glassfish.grizzly.http.util.TimeStamp;

import com.Shell.dao.IDataDAO;
import com.Shell.vo.Data;

public class DataDAOImpl implements IDataDAO{

	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public DataDAOImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean addData(float cpu, float mem, float net)
	{
		System.out.println(cpu);
		System.out.println(mem);
		System.out.println(net);
		boolean isAdd = false;
		try
		{
			String sql = "INSERT INTO data(cpu,net,mem,date) VALUES(?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setFloat(1, cpu);
			pstm.setFloat(2, net);
			pstm.setFloat(3, mem);
			pstm.setTimestamp(4, new Timestamp(new Date().getTime()));
			if (pstm.executeUpdate() > 0)
			{
				isAdd = true;
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
		return isAdd;
	}
	
	public List<Data> getData()
	{
		List<Data> datas = new ArrayList<>();
		try
		{
			String sql = "SELECT * FROM data ORDER BY date DESC LIMIT 10";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				Data data = new Data();
				data.setCpu(rs.getFloat("cpu"));
				data.setDate((Date)rs.getDate("date"));
				data.setMem(rs.getFloat("mem"));
				data.setNet(rs.getFloat("net"));
				datas.add(data);
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
		return datas;
	}
}
