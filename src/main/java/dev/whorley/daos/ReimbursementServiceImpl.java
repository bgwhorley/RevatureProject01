package dev.whorley.daos;

import java.util.ArrayList;
import java.util.List;

import dev.whorley.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	ReimbursementDAOImpl r = new ReimbursementDAOImpl();
	
	@Override
	public List<Reimbursement> getReimbursements(String username) {
		List<Reimbursement> ret = new ArrayList<Reimbursement>();
		ret = r.getReimbursements(username);
		return ret;
	}

	public List<Reimbursement> getAllReimbursments(){
		List<Reimbursement> ret = new ArrayList<Reimbursement>();
		ret =  r.getAllUserReimbursements();
		return ret;
	}
	
	@Override
	public boolean createReimbursement(String username, int amount, String description) {
		boolean ret = r.createReimbursement(username, amount, description);
		if(ret) {
			return true;
		}
		return false;
	}

	@Override
	public boolean approveReimbursement(int rqid) {
		return r.approveReimbursement(rqid);
	}

	@Override
	public boolean denyReimbursement(int rqid) {
		return r.denyReimbursement(rqid);
	}	
	
}
