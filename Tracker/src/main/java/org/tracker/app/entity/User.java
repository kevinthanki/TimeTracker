package org.tracker.app.entity;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String username;
	private String password;
	private String name;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
}
