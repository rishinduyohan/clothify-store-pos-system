package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
}
