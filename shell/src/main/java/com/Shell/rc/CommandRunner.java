package com.Shell.rc;

import com.Shell.vo.Command;

public class CommandRunner {

	public void runCommand(Command command)
	{
		Process process = null;  
        try 
        {  
            process = Runtime.getRuntime().exec(command.getCommand());  
        } catch (Exception e) 
        {  
            e.printStackTrace();  
        }  

	}
}
