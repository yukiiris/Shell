package com.Shell.utils;


import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.rc.CommandRunner;
import com.Shell.vo.User;
import com.Shell.utils.CommandPareser;

public class RunJob implements Job{
	
	public  static String command;
	public static int cid;
	
	public RunJob()
	{
		
	}
	

	public static void parse()
	{
		User user = new User();
		try
		{
			int uid = DAOFactory.getICommandDAOInstance().findUidByCid(cid);
			user = DAOFactory.getIUserDAOInstance().findUserById(uid);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		CommandPareser commandPareser = new CommandPareser(command, user);
		commandPareser.parse();
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println(new Date());
//		User user = new User();
//		try
//		{
//			int uid = DAOFactory.getICommandDAOInstance().findUidByCid(cid);
//			user = DAOFactory.getIUserDAOInstance().findUserById(uid);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
		String result = null;
		CommandRunner commandRunner = new CommandRunner(command);
		//CommandPareser commandPareser = new CommandPareser(command, user);
		result = commandRunner.runCommand();
		//commandPareser.parse();
		
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
