package com.todo.service.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Todo {
	private String id;
	private String title;
	private Boolean isDone;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean isDone() {
		return isDone;
	}
	
	public void setDone(Boolean isDone) {
		this.isDone = isDone;
	}
}
