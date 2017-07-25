package com.Shell.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.Command;

public class CommandSchedule{

		public static Date date;
		static int count = 0;
		static Command command = null;
		static boolean isClose = false;
		public CommandSchedule()
		{
			start();
		}
	
	   public static String formatDateByPattern(Date date,String dateFormat){  
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
	        String formatTimeStr = null;  
	        if (date != null) {  
	            formatTimeStr = sdf.format(date);  
	        }  
	        return formatTimeStr;  
	    }  
	    public static String getCron(java.util.Date  date){  
	        String dateFormat="ss mm HH dd MM ? yyyy";  
	        return formatDateByPattern(date, dateFormat);  
	    }  
	
	@SuppressWarnings("deprecation")
	public static void start() {

		++count;

		
        try {  
        	command = DAOFactory.getICommandDAOInstance().getNext();
        	SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String d = format.format(command.getDate());  
            date=format.parse(d); 
        	System.out.println("下次任务时间：" + date);  
        	System.out.println("现在是:" + new Date());
        } catch (Exception e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }
   
        Scheduler scheduler = null;  
        try {  
            scheduler = StdSchedulerFactory.getDefaultScheduler();  
        } catch (SchedulerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        
        try {  
        	RunJob.command = command.getCommand();
        	RunJob.cid = command.getCid();
        	isClose = false;
            scheduler.start();  
        } catch (SchedulerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  

        if (count >= 2) {  
            System.out.println("说明这里是第二次以上的的定时任务:"+count);  
            try {  
                scheduler.shutdown();   //关闭之前的调度任务  
            } catch (SchedulerException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }    

            //重新开启任务  
            try {  
                scheduler = StdSchedulerFactory.getDefaultScheduler();  
            } catch (SchedulerException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            try {  
            	RunJob.command = command.getCommand();
            	RunJob.cid = command.getCid();
                scheduler.start();  
                isClose = false;
            } catch (SchedulerException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
              
        	if (command.getDate() == 0 || command.getDate() < new Date().getTime())
        	{
        		try {
					scheduler.shutdown();
					isClose = true;
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();				
				}
        	}
            //开启新的调度任务  
            String cron = getCron(date);
            System.out.println(cron);
            if (cron == null)
        	{
        		try {
					scheduler.shutdown();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
        	}
            JobDetail job = JobBuilder.newJob(RunJob.class).withIdentity("job1", "group1").build();
            CronTrigger cronTrigger = null;
			try {
				cronTrigger = new CronTriggerImpl("as", "asd", cron);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
            // 你修改下面的 表达式 就能满足了  
            // 时间格式: <!-- s m h d m w(?) y(?) -->, 分别对应: 秒>分>小时>日>月>周>年  

            try {  
            	if (!isClose)
                scheduler.scheduleJob(job, cronTrigger);  
            } catch (SchedulerException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } else {  
        	if (command.getDate() == 0 || command.getDate() < new Date().getTime())
        	{
        		try {
        			isClose = true;
					scheduler.shutdown();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
        	}
        	String cron = getCron(date);
        	System.out.println(cron);
        	if (cron == null)
        	{
        		try {
        			isClose = true;
					scheduler.shutdown();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					System.out.println(1111);
					e.printStackTrace();
					
				}
        	}
        	 JobDetail job = JobBuilder.newJob(RunJob.class).withIdentity("job1", "group1").build();
        	 CronTrigger cronTrigger = null;
             try {
            	 if (cron != null)
				cronTrigger = new CronTriggerImpl("as", "asd", cron);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
             try {  
       
            	 if (!isClose)
                 scheduler.scheduleJob(job, cronTrigger);  
             } catch (SchedulerException e) {  
                 // TODO Auto-generated catch block  
                 e.printStackTrace();  
             } 
        }
	}
//        try {
//            
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.start();
//
//            
//            JobDetail job = JobBuilder.newJob(RunJob.class).withIdentity("job1", "group1").build();
//
//            String cron = getCron(date);
//            System.out.println(cron);
//            @SuppressWarnings("deprecation")
//			CronTrigger cronTrigger = new CronTriggerImpl("as", "asd", cron);
//            
////            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
////                    .withIntervalInSeconds(5).repeatForever();
////            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
////                    .startNow().withSchedule(simpleScheduleBuilder).build();
//
//            
//            scheduler.scheduleJob(job, cronTrigger);
//            
//           
////            try {
////                TimeUnit.MINUTES.sleep(0);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//
//        
//            //scheduler.shutdown();
//
//        } catch (SchedulerException se) {
//            //logger.error(se.getMessage(), se);
//        } catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    }
}
