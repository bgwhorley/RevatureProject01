package dev.whorley.models;

public class Reimbursement {
	
	private int amount;
	private int id;
	private String status;
	private String description;
	private String username;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Reimbursement(int id, String uname, String description, String status, int amount, String timestamp) {
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.username = uname;
		this.id = id;
		this.timestamp = timestamp;
	}	

	public Reimbursement() {
		
	}	
	
	public String toString() {
		return(this.username + ":" + this.amount + ":" + this.status + ":" + this.description );
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
