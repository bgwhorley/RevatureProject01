package dev.whorley.daos;

import dev.whorley.models.Employee;

public interface EmployeeService {
	public Employee logIn(String username, String password);
	public Employee getEmployee(String username);
}
