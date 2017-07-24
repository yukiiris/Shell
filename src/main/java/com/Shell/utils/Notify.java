package com.Shell.utils;


import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

public class Notify {
	
 
  
    public void start() throws Exception {  
  
        int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED  
                | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;  
  
 
        boolean watchSubtree = true;  
 
        JNotify  
                .addWatch("/home/moltres/test/", mask, watchSubtree, new Listener());  
  
  
        //Thread.sleep(1000000);  
  
        // to remove watch the watch  
       // boolean res = JNotify.removeWatch(watchID);  
//        if (!res) {  
// 
//        }  
    }  
  
    class Listener implements JNotifyListener {  
        public void fileRenamed(int wd, String rootPath, String oldName,  
                String newName) {  
            print("renamed " + rootPath + " : " + oldName + " -> " + newName);  
        }  
  
        public void fileModified(int wd, String rootPath, String name) {  
        	
        }  
  
        public void fileDeleted(int wd, String rootPath, String name) {  
        	CommandPareser.create = 1;
            CommandPareser.name = name;
            RunJob.parse();
            print("delmkdir eted " + rootPath + " : " + name);  
        }  
  
        public void fileCreated(int wd, String rootPath, String name) {  
            CommandPareser.create = 1;
            CommandPareser.name = name;
            RunJob.parse();
            print("create " + rootPath + " : " + name);  
        }  
  
        void print(String msg) {  
            System.err.println(msg);  
        }  
    }
}
