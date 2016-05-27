package com.valmar.silliconvalley.dao;

import java.util.List;

import com.valmar.silliconvalley.model.Employee;

public interface EmployeeDao {
	Employee findById(int id);
	
	boolean findBySsn(String ssn); 
	 
    void saveEmployee(Employee employee);
     
    void deleteEmployeeById(int id);
     
    List<Employee> findAllEmployees();
}
