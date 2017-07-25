package com.Shell.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

	private int gid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}

}
