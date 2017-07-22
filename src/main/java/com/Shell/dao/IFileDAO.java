package com.Shell.dao;

import java.util.List;

import com.Shell.vo.File;

public interface IFileDAO {

	public List<File> getAll();
	public File findFileByName(String name);
	public boolean addFile(File file);
	public boolean deleteFile(String name);
	
}
