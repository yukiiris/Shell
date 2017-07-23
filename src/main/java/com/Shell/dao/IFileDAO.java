package com.Shell.dao;

import java.util.List;

import com.Shell.vo.File;

public interface IFileDAO {

	public List<File> getAll();
	public File findFileByName(String name);
	public boolean addFiles(List<File> files);
	public boolean deleteFile(String name);
}
