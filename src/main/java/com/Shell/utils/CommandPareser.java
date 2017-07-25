package com.Shell.utils;

import java.util.ArrayList;
import java.util.List;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.File;
import com.Shell.vo.Group;
import com.Shell.vo.User;

public class CommandPareser {

	private static String command;
	private static List<File> files;
	private static User user;
	public static int create = 0;
	public static String name;
	
	public CommandPareser(String command, User user)
	{
		CommandPareser.command = command;
		CommandPareser.user = user;
		try
		{
			files = DAOFactory.getIFileDAOInstance().getAll();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public CommandPareser (User user)
	{
		CommandPareser.user = user;
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


		if (words[0].equals("mkdir"))
		{
			if (words[1].equals("--version") || words[1].equals("--help") || words[1].equals("-v") || words[1].equals("-h"))
			{
				return true;
			}
			else
			{
				if (create != 0)
				{
					mikdir(words, name);
					create = 0;
				}
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
				return touch(words, name);
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
				return cat(words, name);
			}
		}
		else if (words[0].equals("bash"))
		{
			
		}
		else if (words[0].equals("rm"))
		{
			if (words[1].equals("--version") || words[1].equals("--help") || words[1].equals("-v") || words[1].equals("-h"))
			{
				return true;
			}
			else
			{
				if (create != 0)
				{
					rm(words, name);
					create = 0;
				}
				return true;
			}
		}
		return flag;
	}
	
	public static void mikdir(String[] words, String name)
	{
		List<File> files = new ArrayList<>();
		String intAthority ;
		try
		{
			if (command.contains("-m") || command.equals("--mode"))
			{
				intAthority = words[2];
				
	
				File file = new File();
				file.setGid(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()));
				file.setName(name);
				file.setUid(user.getUid());
				files.add(file);
				
			}
			else
			{
				intAthority = 644 + "";
				File file = new File();
				file.setGid(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()));
				file.setName(name);
				file.setUid(user.getUid());
				files.add(file);
				parseAuthority(intAthority, files);
			}
			DAOFactory.getIFileDAOInstance().addFiles(files);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean touch(String[] words, String name)
	{
		for (File file : files)
		{
			if (file.getName().equals(name))
			{
				if (getAuthority(user, file, "w"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		if (create == 0)
		{
			return true;
		}
		boolean isAdd = false;
		try
		{
			List<File> f = new ArrayList<>();
			File file = new File();
			file.setGid(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()));
			file.setName(name);
			file.setUid(user.getUid());
			f.add(file);
			isAdd = DAOFactory.getIFileDAOInstance().addFiles(f);
			parseAuthority(755 + "", f);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return isAdd;
//		for (int i = 1; i < words.length; i++)
//		{
//			if (!words[i].contains("-"))
//			{
//				boolean already = false;
//				for (File file : files)
//				{
//					if (file.getName().equals(words[i]))
//					{
//						if (getAuthority(user, file, "write"))
//						{
//							already = true;
//						}
//						else
//						{
//							return false;
//						}
//					}
//				}
//				if (!already)
//				{
//					try
//					{
//						List<File> f = new ArrayList<>();
//						File file = new File();
//						file.setGid(Integer.parseInt(user.getGroup().split(" ")[0]));
//						file.setName(words[i]);
//						file.setUid(user.getUid());
//						f.add(file);
//						DAOFactory.getIFileDAOInstance().addFiles(f);
//						parseAuthority(755 + "");
//					} 
//					catch (Exception e) 
//					{
//						e.printStackTrace();
//					}
//				}
//			}
//		}
	//	return true;
	}
	
	public boolean ls(String[] words)
	{
		return true;
	}
	
	public boolean cat(String[] words, String name)
	{
		if (command.contains(">"))
		{
			try
			{
				List<File> files = new ArrayList<>();
				File file = new File();
				file.setGid(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()));
				file.setName(name);
				file.setUid(user.getUid());
				files.add(file);
			
				DAOFactory.getIFileDAOInstance().addFiles(files);
				parseAuthority(755 + "", files);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{

			for (File file : files)
			{
				if (file.getName().equals(name) && getAuthority(user, file, "r"))
				{
					return true;
				}
			}
		}
		return true;
	}
	
	public void bash(String[] words)
	{
		
	}
	
	public boolean rm(String[] words, String name)
	{
		for (File file : files)
		{
			if (file.getName().equals(name) && getAuthority(user, file, "r"))
			{
				try {
					return DAOFactory.getIFileDAOInstance().deleteFile(name);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static boolean getAuthority(User user, File file, String authority)
	{
		String result = null;
		try
		{
			if (DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()) == 1)
			{
				return true;
			}
		
			result = DAOFactory.getIUserDAOInstance().findAuthorityById(user.getUid(), file.getName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (result.contains(authority))
		{
			return true;
		}
		
		try 
		{
			result = DAOFactory.getIGroupDAOInstance().findAuthorityById(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (result.contains(authority))
		{
			return true;
		}
		return false;
	}
	
	
	public static String getAuList(int i)
	{
		
		if (i == 0)
		{
			return null;
		}
		else if (i == 1)
		{
			return "x";
		}
		else if (i == 2)
		{
			return "w";
		}
		else if (i == 4)
		{
			return "r";
		}
		else if (i == 3)
		{
			return "xw";
		}
		else if (i == 5)
		{
			return "rw";
		}
		else if (i == 6)
		{
			
			return "xr";
		}
		else if (i == 7)
		{
			return "xrw";
		}
		return null;
	}
	
	public static void parseAuthority(String intAthority, List<File> file)
	{
		String[] authorities = intAthority.split("");
		for (int i = 0; i < authorities.length; i++)
		{
			try {
				if (authorities[i].equals("7"))
				{
					String authority = getAuList(7);
					
					if (i == 0)
					{
					
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);
					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("5"))
				{
					String authority = getAuList(5);
					
					if (i == 0)
					{
						
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);
					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("6"))
				{
					String authority = getAuList(6);
					
					if (i == 0)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);

					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("3"))
				{
					String authority = getAuList(3);
					
					if (i == 0)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);

					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("4"))
				{
					String authority = getAuList(4);
					
					if (i == 0)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);

					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("2"))
				{
					String authority = getAuList(2);
					
					if (i == 0)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);

					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}					}
				}
				else if (authorities[i].equals("1"))
				{
					String authority = getAuList(1);
					
					if (i == 0)
					{
						DAOFactory.getIUserDAOInstance().setAuthority(user.getUid(), authority, file);

					}
					else if (i == 1)
					{
						DAOFactory.getIGroupDAOInstance().setAuthority(DAOFactory.getIUserDAOInstance().findGidByUid(user.getUid()), authority, file.get(0).getName());
					}
					else if (i == 2)
					{
						for (Group group : DAOFactory.getIGroupDAOInstance().getAllGroup())
						{
							DAOFactory.getIGroupDAOInstance().setAuthority(group.getGid(), authority, file.get(0).getName());
						}
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
