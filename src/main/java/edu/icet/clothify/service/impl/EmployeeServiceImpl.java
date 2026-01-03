package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.EmployeeDTO;
import edu.icet.clothify.model.entity.Employee;
import edu.icet.clothify.repository.EmployeeRepository;
import edu.icet.clothify.repository.impl.EmployeeRepositoryImpl;
import edu.icet.clothify.service.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setPosition(employeeDTO.getPosition());
        newEmployee.setContactNumber(employeeDTO.getContactNumber());
        newEmployee.setEmail(employeeDTO.getEmail());
        return employeeRepository.addEmployee(newEmployee);
    }

    @Override
    public boolean deleteEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.deleteEmployee(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getPosition(),employeeDTO.getContactNumber(),employeeDTO.getEmail()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.updateEmployee(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getPosition(),employeeDTO.getContactNumber(),employeeDTO.getEmail()));
    }

    @Override
    public EmployeeDTO searchEmployee(Long id) {
        try {
            Employee employee = employeeRepository.searchEmployee(id);
            return new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getPosition(),
                    employee.getContactNumber(),
                    employee.getEmail()
            );
        } catch (Exception _) {
            new Alert(Alert.AlertType.ERROR,"Invalid Employee id!").show();
            return null;
        }
    }
}
