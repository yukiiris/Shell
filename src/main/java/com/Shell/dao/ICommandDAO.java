package com.Shell.dao;

import java.util.List;

import com.Shell.vo.Command;

public interface ICommandDAO {

	public boolean addCommand(Command command);
	public boolean ajustCommand(Command command);
	public List<Command> getAll(int uid);
	public Command getNext();
	public boolean Complete(int cid);
}
