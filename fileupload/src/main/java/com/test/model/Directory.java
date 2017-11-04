package com.test.model;

import java.io.Serializable;

public class Directory implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String comments;

	public Directory() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Directory [code=" + code + ", name=" + name + ", comments=" + comments + "]";
	}
	
	
	
	

}