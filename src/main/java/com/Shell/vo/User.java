package com.Shell.vo;

public class User {

	private int uid;
	private String password;
	private String group;
	private String name;
	
	public User()
	{
		
	}
	
	public User(String name, String group, String password)
	{
		this.group = group;
		this.name = name;
		this.password = password;
	}
	
	public int getUid() {
		return uid;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
