package com.Shell.Jersey.Impl;

import java.util.ArrayList;
import java.util.List;

import com.Shell.Jersey.API.FileAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.CommandPareser;
import com.Shell.vo.File;

public class FileImpl implements FileAPI{

	@Override
	public List<File> getAllFile() {
		List<File> files = new ArrayList<>();
		try
		{
			files = DAOFactory.getIFileDAOInstance().getAll();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return files;
	}

	@Override
	public boolean changeMode(String authority, File file) {
		List<File> files = new ArrayList<>();
		files.add(file);

		CommandPareser.parseAuthority(authority, files);;
		return false;
	}

	@Override
	public boolean changeGroup(File file, int gid) {
		boolean isChange = false;
		try
		{
			isChange = DAOFactory.getIFileDAOInstance().changeGroup(gid, file.getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isChange;
	}

}
