package com.Shell.rc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.Shell.dao.factory.DAOFactory;

public class CommandRunner {
	
	private String command;

	public CommandRunner(String command)
	{
		this.command = command;
	}
	
	public String runCommand()
	{
		Process process = null; 
		String result = null;
		String error = null;
		int c = 0;
        try 
        {  
        	System.out.println(command);
            process = Runtime.getRuntime().exec(command);  
	        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
	        StringBuffer sb = new StringBuffer();  
	        String line;  
	        while ((line = br.readLine()) != null) {  
	            sb.append(line).append("\n");  
	        }  
	        result = sb.toString();
	        System.out.println(result); 
	        
	        
            BufferedReader brError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8"));  
            String errline = null;  
            StringBuffer serror = new StringBuffer();
            while ((errline = brError.readLine()) != null) {  
            	serror.append(line);
                 System.out.println(errline);  
            } 
            error = serror.toString();
            c = process.waitFor();
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }  

//        try
//        {
//            
//	        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
//	        StringBuffer sb = new StringBuffer();  
//	        String line;  
//	        while ((line = br.readLine()) != null) {  
//	            sb.append(line).append("\n");  
//	        }  
//	        result = sb.toString();
//	        System.out.println(result);  
//        }   
//    	catch (Exception e) 
//        {  
//    		e.printStackTrace();  
//        }  
        return c == 0 ? result : error;
	}
	
//	public static void main(String[] args)
//	{
//		String command = "mkdir test";
//		CommandRunner commandRunner = new CommandRunner(command);
//		commandRunner.runCommand();
//	}
}
