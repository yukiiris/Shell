package com.Shell.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String DBDRIVER = "org.mariadb.jdbc.Driver";
	private static final String DBURL = "jdbc:mariadb://localhost:3306/shell";
	private static final String DBUSER = "moltres";
	private static final String DBPASSWORD = "";
	private Connection connection = null;
	
	public DatabaseConnection() throws ClassNotFoundException, SQLException
	{
		try
		{
			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public void close() throws SQLException
	{
		if (connection != null)
		{
			connection.close();
		}
	}
}