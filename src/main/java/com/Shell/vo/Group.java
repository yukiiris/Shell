package com.Shell.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

	private int gid;
	private String name;
	private String users;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
}
