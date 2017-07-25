package com.Shell.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Shell.dao.factory.DAOFactory;

public class DataJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		try
		{
			DAOFactory.getIDataDAOInstance().addData(Chart.getCPU(), Chart.getMem(), Chart.getNet());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
