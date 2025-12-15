package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    Employee searchEmployee(Long id);
}
