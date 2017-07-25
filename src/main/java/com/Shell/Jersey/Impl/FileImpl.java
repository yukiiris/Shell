package com.Shell.Jersey.Impl;

import java.util.ArrayList;
import java.util.List;

import com.Shell.Jersey.API.FileAPI;
import com.Shell.utils.CommandPareser;
import com.Shell.vo.File;

public class FileImpl implements FileAPI{

	@Override
	public List<File> getAllFile() {
		
		return null;
	}

	@Override
	public boolean changeMode(String authority, File file) {
		List<File> files = new ArrayList<>();
		files.add(file);

		CommandPareser.parseAuthority(authority, files);;
		return false;
	}

}
