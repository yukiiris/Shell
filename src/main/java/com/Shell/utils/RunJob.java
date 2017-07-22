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
		
//		try {
//			command = DAOFactory.getICommandDAOInstance().getNext();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	

	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println(new Date());
		 CommandRunner commandRunner = new CommandRunner(command);
		 commandRunner.runCommand();
		 
			try {
				DAOFactory.getICommandDAOInstance().Complete(cid);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		Schedule.date = command.getDate();
//		System.out.println(command.getDate());
	}
}
