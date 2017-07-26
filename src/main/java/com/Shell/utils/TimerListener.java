package com.Shell.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.contentobjects.jnotify.JNotify;

public class TimerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CommandSchedule.start();
		//DataSchedule.start();
		try {
			new Notify().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
