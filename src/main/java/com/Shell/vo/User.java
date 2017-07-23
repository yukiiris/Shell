package com.Shell.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private int uid;
	private String password;
	private String group;
	private String name;
	private String r;
	private String w;
	private String x;
	
	public User()
	{
		
	}
	
	public User(String name, String group, String password)
	{
		this.group = group;
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

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
}
