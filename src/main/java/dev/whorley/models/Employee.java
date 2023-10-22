package dev.whorley.models;

public class Employee {
	private String username;
	private String name;
	private String password;
	private int manager;

	public Employee() {
		
	}
	
	public String toString() {
		return(this.username + ":" + this.name + ":" + this.password + ":" + this.manager + "\n");
	}
	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public Employee(String username, String name, String password, int manager) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.manager = manager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
