package dev.whorley.daos;

import java.util.List;

import dev.whorley.models.Employee;

public interface EmployeeDAO {
	//====	create	====
	public boolean createEmployee(String username, String name, String password, int mngr);
	
	//====	read	====
	public Employee getEmployee(String username);
	public List<Employee> getAllEmployees();
	
	//====	update	====
	public boolean setEmployeeName(String username, String name);
	public boolean setEmployeePassword(String username, String password);
	public boolean setManagerStatus(String username, int status);
	
	
	//====	delete	====
	//no delete functions

}
