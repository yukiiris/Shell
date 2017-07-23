package com.Shell.utils;


import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.rc.CommandRunner;


public class RunJob implements Job{
	
	public  static String command;
	public static int cid;
	
	public RunJob()
	{
		
	}
	

	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println(new Date());
		String result = null;
		CommandRunner commandRunner = new CommandRunner(command);
		result = commandRunner.runCommand();
		
		try
		{
			DAOFactory.getICommandDAOInstance().Complete(cid);
			DAOFactory.getIResultDAOInstance().addResult(cid, result);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
