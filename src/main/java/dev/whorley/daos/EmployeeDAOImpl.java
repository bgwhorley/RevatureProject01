package dev.whorley.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.whorley.models.Employee;
import util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	public boolean createEmployee(String username, String name, String password, int mngr) {
		Connection conn = ConnectionUtil.getConnection();

		String sql = "insert into employees (employee_id, name, user_name, password, is_manager)"
				+ " values (EMPLOYEES_EMPL_ID_SEQ.nextval, "
				+ "?, "
				+ "?,"
				+ "?,"
				+ "?)";			//assume accounts active upon creation
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, Integer.toString(mngr));
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

	public Employee getEmployee(String username) {
		Connection conn = ConnectionUtil.getConnection();
		
		List<Employee> emplist = new ArrayList<Employee>();
		String sql = "select * from employees where employee_id = "
				+ "(select employee_id from employees where user_name = ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emplist.add(new Employee(
						rs.getString("USER_NAME"),
						rs.getString("NAME"),
						rs.getString("PASSWORD"),
						rs.getInt("IS_MANAGER")
						));				
			}
			if(emplist.size() >0)
				return emplist.get(0);
			else return null;
			
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

	public List<Employee> getAllEmployees() {
		Connection conn = ConnectionUtil.getConnection();
		
		List<Employee> emplist = new ArrayList<Employee>();
		String sql = "select * from employees";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emplist.add(new Employee(
						rs.getString("NAME"),
						rs.getString("USER_NAME"),
						rs.getString("PASSWORD"),
						rs.getInt("IS_MANAGER")
						));				
			}
			return emplist;
			
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

	public boolean setEmployeeName(String username, String name) {
		Connection conn = ConnectionUtil.getConnection();

		String sql = "update employees set name = ? where user_name = ?";			//assume accounts active upon creation
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, username);
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

	public boolean setEmployeePassword(String username, String password) {
		Connection conn = ConnectionUtil.getConnection();

		String sql = "update employees set password = ? where user_name = ?";			//assume accounts active upon creation
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
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

	public boolean setManagerStatus(String username, int status) {
		Connection conn = ConnectionUtil.getConnection();

		String sql = "update employees set is_manager = ? where user_name = ?";			//assume accounts active upon creation
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, status);
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
