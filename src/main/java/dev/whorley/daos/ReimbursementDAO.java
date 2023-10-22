package dev.whorley.daos;

import java.util.List;

import dev.whorley.models.Reimbursement;

public interface ReimbursementDAO {
	//====	create	====
	public boolean createReimbursement(String username, int amount, String description);
	
	//=====	read	====
	public List<Reimbursement> getReimbursements(String username);
	public List<Reimbursement> getAllUserReimbursements();
	
	//====	update	====
	public boolean denyReimbursement(int rqid);
	public boolean approveReimbursement(int rqid);
	
	//delete
	//no delete functions
	
}
