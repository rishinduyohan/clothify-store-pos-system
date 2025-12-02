package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.EmployeeDTO;
import edu.icet.clothify.model.entity.Employee;
import edu.icet.clothify.repository.EmployeeRepository;
import edu.icet.clothify.repository.impl.EmployeeRepositoryImpl;
import edu.icet.clothify.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
    ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @Override
    public ObservableList<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        for (Employee employee : employees) {
            employeeDTOS.add(
                    new EmployeeDTO(
                            employee.getEmployeeId(),
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getPosition(),
                            employee.getContactNumber(),
                            employee.getEmail()
                    )
            );
        }
        return employeeDTOS;
    }
}
