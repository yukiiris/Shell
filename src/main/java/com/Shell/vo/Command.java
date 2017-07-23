package com.Shell.vo;


import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.Shell.utils.CustomDateSerializer;

@XmlRootElement
public class Command {

	private int uid;
	private String command;
	private int status;
	private long date;
	private int cid;

	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int i) {
		this.status = i;
	}
	@JsonSerialize(using = CustomDateSerializer.class) 
	public long getDate() {
		return date;
	}
	@JsonSerialize(using = CustomDateSerializer.class) 
	public void setDate(long date) {
		this.date = date;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
}
