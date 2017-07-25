package com.Shell.Jersey.Impl;

import java.util.ArrayList;
import java.util.List;

import com.Shell.Jersey.API.CommandAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.CommandPareser;
import com.Shell.utils.CommandSchedule;
import com.Shell.vo.Command;
import com.Shell.vo.User;

public class CommandImpl implements CommandAPI{

	@Override
	public boolean addCommand(Command command) {
		User user = new User();
		boolean isAdd = false;
		try
		{
			user = DAOFactory.getIUserDAOInstance().findUserById(command.getUid());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		CommandPareser pareser = new CommandPareser(command.getCommand(), user);
		if (!pareser.parse())
		{
			return false;
		}
		try
		{
			isAdd = DAOFactory.getICommandDAOInstance().addCommand(command);
			CommandSchedule.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isAdd;
	}

	@Override
	public boolean ajustCommand(Command command) {
		User user = new User();
		boolean isAdd = false;
		try
		{
			user = DAOFactory.getIUserDAOInstance().findUserById(command.getUid());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		CommandPareser pareser = new CommandPareser(command.getCommand(), user);
		if (!pareser.parse())
		{
			return false;
		}
		try
		{
			isAdd = DAOFactory.getICommandDAOInstance().ajustCommand(command);
			CommandSchedule.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isAdd;
	}

	@Override
	public List<Command> getAll(int uid) {
		List<Command> commands = new ArrayList<>();
		try {
			commands = DAOFactory.getICommandDAOInstance().getAll(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commands;
	}

}
