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
        try 
        {  
            process = Runtime.getRuntime().exec(command);  
            process.waitFor();
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }  

        try
        {
	        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
	        StringBuffer sb = new StringBuffer();  
	        String line;  
	        while ((line = br.readLine()) != null) {  
	            sb.append(line).append("\n");  
	        }  
	        result = sb.toString();
	        System.out.println(result);  
        }   
    	catch (Exception e) 
        {  
    		e.printStackTrace();  
        }  
        return result;
	}
	
	public static void main(String[] args)
	{
		CommandRunner commandRunner = new CommandRunner("echo \"123\"");
		commandRunner.runCommand();
	}
}
