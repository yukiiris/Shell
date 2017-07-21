package com.Shell.rc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandRunner {
	
	private String command;

	public CommandRunner(String command)
	{
		this.command = command;
	}
	
	public void runCommand()
	{
		Process process = null;  
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
	        String result = sb.toString();  
	        System.out.println(result);  
        }   
    	catch (Exception e) 
        {  
    		e.printStackTrace();  
        }  
	}
	
//	public static void main(String[] args)
//	{
//		runCommand("ls");
//		runCommand("moltres");
//	}
}
