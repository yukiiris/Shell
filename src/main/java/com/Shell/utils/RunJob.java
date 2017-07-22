package com.Shell.utils;


import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.rc.CommandRunner;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

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
		String result = null;
		 CommandRunner commandRunner = new CommandRunner(command);
		 result = commandRunner.runCommand();
		 
			try {
				DAOFactory.getICommandDAOInstance().Complete(cid);
				DAOFactory.getIResultDAOInstance().addResult(cid, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		Schedule.date = command.getDate();
//		System.out.println(command.getDate());
	}


//	public static void main(String[] args) { 
//        try {  
//            new RunJob().sample();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        // System.out.println(System.getProperty("java.library.path"));  
//    }  
//  
//    public void sample() throws Exception {  
//        // path to watch  
//        //String path = System.getProperty("/home/moltres/gothub/Shell/");  
//  
//        // watch mask, specify events you care about,  
//        // or JNotify.FILE_ANY for all events.  
//        int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED  
//                | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;  
//  
//        // watch subtree?  
//        boolean watchSubtree = true;  
//  
//        // add actual watch  
//        int watchID = JNotify  
//                .addWatch("/home/moltres/github/Shell/", mask, watchSubtree, new Listener());  
//  
//        // sleep a little, the application will exit if you  
//        // don't (watching is asynchronous), depending on your  
//        // application, this may not be required  
//        Thread.sleep(1000000);  
//  
//        // to remove watch the watch  
//        boolean res = JNotify.removeWatch(watchID);  
//        if (!res) {  
//            // invalid watch ID specified.  
//        }  
//    }  
//  
//        //可以在下面的监控方法中添加自己的代码。比如在fileModified中添加重新加载配置文件的代码  
//    class Listener implements JNotifyListener {  
//        public void fileRenamed(int wd, String rootPath, String oldName,  
//                String newName) {  
//            print("renamed " + rootPath + " : " + oldName + " -> " + newName);  
//        }  
//  
//        public void fileModified(int wd, String rootPath, String name) {  
//            print("modified " + rootPath + " : " + name);  
//        }  
//  
//        public void fileDeleted(int wd, String rootPath, String name) {  
//            print("deleted " + rootPath + " : " + name);  
//        }  
//  
//        public void fileCreated(int wd, String rootPath, String name) {  
//            print("created " + rootPath + " : " + name);  
//        }  
//  
//        void print(String msg) {  
//            System.err.println(msg);  
//        }  
//    }
}
