package dev.whorley.daos;

import dev.whorley.models.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDAOImpl d = new EmployeeDAOImpl();
	
	@Override
	public Employee logIn(String username, String password) {
		Employee user = d.getEmployee(username);
		if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public Employee getEmployee(String username) {
		return d.getEmployee(username);
	}
}
