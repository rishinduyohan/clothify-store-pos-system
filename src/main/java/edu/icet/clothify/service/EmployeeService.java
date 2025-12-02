package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.EmployeeDTO;
import javafx.collections.ObservableList;

import java.util.List;

public interface EmployeeService {
    ObservableList<EmployeeDTO> getAllEmployees();
}
