package dev.whorley.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.whorley.models.Employee;
import dev.whorley.models.Reimbursement;
import util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	public boolean createReimbursement(String username, int amount, String description) {
			Connection conn = ConnectionUtil.getConnection();

			String sql = "insert into reimbursements "
					+ "(reim_id, employee_id, amount, request_status, description, comments, timestamp)"
					+ " values (REIMBURSEMENTS_REIM_ID_SEQ.nextval, "
					+ "(select employee_id from employees where user_name = ?),"
					+ "?,"
					+ "'SUBMITTED',"
					+ "?, "
					+ "'blank',"
					+ "(select CURRENT_TIMESTAMP from dual))";			//assume accounts active upon creation
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, Integer.toString(amount));
				ps.setString(3, description);
				ResultSet rs = ps.executeQuery();
				
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return false;
	}

	public List<Reimbursement> getReimbursements(String username) {
		Connection conn = ConnectionUtil.getConnection();
		
		List<Reimbursement> reimlist = new ArrayList<Reimbursement>();
		String sql = "select * from reimbursements "
				+ "where employee_id = (SELECT employee_id from employees where user_name = ?) order by timestamp desc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimlist.add(new Reimbursement(
						rs.getInt("REIM_ID"),
						username,				
						rs.getString("DESCRIPTION"),
						rs.getString("REQUEST_STATUS"),
						rs.getInt("AMOUNT"),
						rs.getTimestamp("TIMESTAMP").toString()
						));				
			}
			return reimlist;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public List<Reimbursement> getAllUserReimbursements() {
		Connection conn = ConnectionUtil.getConnection();
		
		List<Reimbursement> reimlist = new ArrayList<Reimbursement>();
		String sql = "select * from reimbursements left join employees on employees.employee_id = reimbursements.employee_id";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimlist.add(new Reimbursement(
						rs.getInt("REIM_ID"),
						rs.getString("USER_NAME"),				
						rs.getString("DESCRIPTION"),
						rs.getString("REQUEST_STATUS"),
						rs.getInt("AMOUNT"),
						rs.getTimestamp("TIMESTAMP").toString()
						));				
			}
			return reimlist;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public boolean approveReimbursement(int rqid) {
		Connection conn = ConnectionUtil.getConnection();
	//fix, needs to add history instead of update	
		String sql = "update reimbursements set request_status = 'APPROVED', comments = 'none', timestamp = (select CURRENT_TIMESTAMP from dual) "
				+ "where reim_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rqid);
			ResultSet rs = ps.executeQuery();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean denyReimbursement(int rqid) {
		Connection conn = ConnectionUtil.getConnection();
		
		String sql = "update reimbursements set request_status = 'DENIED', comments = 'none', timestamp = (select CURRENT_TIMESTAMP from dual) "
				+ "where reim_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rqid);
			ResultSet rs = ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
