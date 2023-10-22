package dev.whorley.daos;

import java.util.List;

import dev.whorley.models.Reimbursement;

public interface ReimbursementService {
	public List<Reimbursement> getReimbursements(String username);
	public boolean createReimbursement(String username, int amount, String description);
	public boolean approveReimbursement(int rqid);
	public boolean denyReimbursement(int rqid);
}
