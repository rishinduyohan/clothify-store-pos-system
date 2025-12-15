package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.EmployeeDTO;
import javafx.collections.ObservableList;


public interface EmployeeService {
    ObservableList<EmployeeDTO> getAllEmployees();
    boolean addEmployee(EmployeeDTO employeeDTO);
    boolean deleteEmployee(EmployeeDTO employeeDTO);
    boolean updateEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO searchEmployee(Long id);
}
