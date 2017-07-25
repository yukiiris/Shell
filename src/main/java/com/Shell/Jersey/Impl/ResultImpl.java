package com.Shell.Jersey.Impl;

import java.util.ArrayList;
import java.util.List;

import com.Shell.Jersey.API.ResultAPI;
import com.Shell.dao.factory.DAOFactory;
import com.Shell.vo.Result;

public class ResultImpl implements ResultAPI{

	@Override
	public String getResultById(int cid) {
		String results = null;
		try
		{
			results = DAOFactory.getIResultDAOInstance().findResult(cid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Result> getAllResult(int uid) {
		List<Result> results = new ArrayList<>();
		return null;
	}

}
