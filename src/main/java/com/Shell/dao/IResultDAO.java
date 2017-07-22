package com.Shell.dao;

public interface IResultDAO {

	public String findResult(int cid);
	public boolean addResult(int cid, String result);
}
