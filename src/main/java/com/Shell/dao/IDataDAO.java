package com.Shell.dao;

import java.util.List;

import com.Shell.vo.Data;

public interface IDataDAO {

	public boolean addData(float cpu, float mem, float net);
	public List<Data> getData();
}
