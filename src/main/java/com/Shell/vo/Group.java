package com.Shell.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

	private int gid;
	private String name;
	private String users;
	private String w;
	private String r;
	private String x;
	
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
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
}
