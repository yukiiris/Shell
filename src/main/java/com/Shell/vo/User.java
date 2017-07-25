package com.Shell.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private int uid;
	private String password;
	private String name;

	
	public User()
	{
		
	}
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public void setUid(int uid)
	{
		this.uid = uid;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
