package com.Shell.utils;

import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.File;
import com.Shell.vo.Group;
import com.Shell.vo.User;

public class CommandPareser {

	private String command;
	private List<File> files;
	private User user;
	
	public CommandPareser(String command, User user)
	{
		this.command = command;
		this.user = user;
		try
		{
			files = DAOFactory.getIFileDAOInstance().getAll();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean parse()
	{
		String[] words = command.split(" ");
		boolean flag = false;
		//System.out.println(user.getGroup());
		if (user.getGroup().split(" ")[0].equals("1"))
		{
			return true;
		}
		if (words[0].equals("mkdir"))
		{
			if (words[1].equals("--version") || words[1].equals("--help") || words[1].equals("-v") || words[1].equals("-h"))
			{
				return true;
			}
			else
			{
				mikdir(words);
				return true;
			}
		}
		else if (words[0].equals("touch"))
		{
			if (words[1].equals("--version") || words[1].equals("--help") || words[1].equals("-v") || words[1].equals("-h"))
			{
				return true;
			}
			else
			{
				return touch(words);
			}
		}
		else if (words[0].equals("ls"))
		{
				return true;
		}
		else if (words[0].equals("cat"))
		{
			if (words[1].equals("--version") || words[1].equals("--help") || words[1].equals("-v") || words[1].equals("-h"))
			{
				return true;
			}
			else
			{
				return cat(words);
			}
		}
		else if (words[0].equals("bash"))
		{
			
		}
		else if (words[0].equals("rm"))
		{
			
		}
		return flag;
	}
	
	public void mikdir(String[] words)
	{
		List<File> files = new ArrayList<>();
		String intAthority ;
		if (command.contains("-m") || command.equals("--mode"))
		{
			intAthority = words[2];
			
			for (int i = 3; i <= words.length - 1; i++)
			{
				File file = new File();
				file.setGid(Integer.parseInt(user.getGroup().split(" ")[0]));
				file.setName(words[i]);
				file.setUid(user.getUid());
				files.add(file);
			}
		}
		else
		{
			intAthority = 644 + "";
			for (int i = 0; i <= words.length - 1; i++)
			{
				File file = new File();
				file.setGid(Integer.parseInt(user.getGroup().split(" ")[0]));
				file.setName(words[i]);
				file.setUid(user.getUid());
				files.add(file);
			}
		}
		try
		{
			DAOFactory.getIFileDAOInstance().addFiles(files);
			parseAuthority(intAthority);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean touch(String[] words)
	{
		for (int i = 1; i < words.length; i++)
		{
			if (!words[i].contains("-"))
			{
				boolean already = false;
				for (File file : files)
				{
					if (file.getName().equals(words[i]))
					{
						if (getAuthority(user, file, "write"))
						{
							already = true;
						}
						else
						{
							return false;
						}
					}
				}
				if (!already)
				{
					try
					{
						List<File> f = new ArrayList<>();
						File file = new File();
						file.setGid(Integer.parseInt(user.getGroup().split(" ")[0]));
						file.setName(words[i]);
						file.setUid(user.getUid());
						f.add(file);
						DAOFactory.getIFileDAOInstance().addFiles(f);
						parseAuthority(755 + "");
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	
	public boolean ls(String[] words)
	{
		return true;
	}
	
	public boolean cat(String[] words)
	{
		if (command.contains(">"))
		{
			
		}
		else
		{
			for (int i = 1; i < words.length; i++)
			{
				if (!words[i].contains("-"))
				{
					boolean already = false;
					for (File file : files)
					{
						if (file.getName().equals(words[i]) && getAuthority(user, file, "read"))
						{
							already = true;
						}
					}
					if (!already)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void bash(String[] words)
	{
		
	}
	
	public void rm(String[] words)
	{
		
	}
	
	public static boolean getAuthority(User user, File file, String authority)
	{
		String result = null;
		try 
		{
			result = DAOFactory.getIUserDAOInstance().findAuthorityByName(user.getName(), authority);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		String[] results = result.split(" ");
		for (String string : results)
		{
			if (string.equals(file.getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public List<Integer> getUserFromGroup(String gs)
	{
		String[] groups = gs.split(" ");
		int gid = Integer.parseInt(groups[0]);
		List<Integer> users = new ArrayList<>();
		Group group = new  Group();
		try
		{
			group = DAOFactory.getIGroupDAOInstance().findGroupById(gid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String[] user = group.getUsers().split(" ");
		for (String s : user)
		{
			users.add(Integer.parseInt(s));
		}
		return users;
	}
	
	public List<String> getAuList(int i)
	{
		List<String> authority = new ArrayList<>();
		if (i == 0)
		{
			return authority;
		}
		else if (i == 1)
		{
			authority.add("execute");
			return authority;
		}
		else if (i == 2)
		{
			authority.add("write");
			return authority;
		}
		else if (i == 4)
		{
			authority.add("read");
			return authority;
		}
		else if (i == 3)
		{
			authority.add("execute");
			authority.add("write");
			return authority;
		}
		else if (i == 5)
		{
			authority.add("read");
			authority.add("write");
			return authority;
		}
		else if (i == 6)
		{
			authority.add("execute");
			authority.add("read");
			return authority;
		}
		else if (i == 7)
		{
			authority.add("execute");
			authority.add("write");
			authority.add("read");
			return authority;
		}
		return null;
	}
	
	public 	void parseAuthority(String intAthority)
	{
		String[] authorities = intAthority.split("");
		for (int i = 0; i < authorities.length; i++)
		{
			try {
				if (authorities[i].equals("7"))
				{
					List<String> authority = getAuList(7);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("5"))
				{
					List<String> authority = getAuList(5);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("6"))
				{
					List<String> authority = getAuList(6);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("3"))
				{
					List<String> authority = getAuList(3);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("4"))
				{
					List<String> authority = getAuList(4);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("2"))
				{
					List<String> authority = getAuList(2);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("1"))
				{
					List<String> authority = getAuList(1);
					
					if (i == 0)
					{
						List<Integer> users = new ArrayList<>();
						users.add(user.getUid());
						DAOFactory.getIUserDAOInstance().setAuthority(users, authority, files);
					}
					else if (i == 1)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(getUserFromGroup(user.getGroup()), authority, files);
					}
					else if (i == 2)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().getAllId(), authority, files);
					}
				}
				else if (authorities[i].equals("0"))
				{
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
