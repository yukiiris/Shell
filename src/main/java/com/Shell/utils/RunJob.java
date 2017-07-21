package com.Shell.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.rc.CommandRunner;
import com.Shell.vo.Command;

public class RunJob implements Job{
	
	private Command command;
	
	public RunJob()
	{
		try {
			command = DAOFactory.getICommandDAOInstance().getNext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		 CommandRunner commandRunner = new CommandRunner(command.getCommand());
		 commandRunner.runCommand();
			try {
				command = DAOFactory.getICommandDAOInstance().getNext();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
