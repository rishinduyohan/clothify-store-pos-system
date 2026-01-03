package edu.icet.clothify.controller;

import edu.icet.clothify.model.dto.EmployeeDTO;
import edu.icet.clothify.service.EmployeeService;
import edu.icet.clothify.service.impl.EmployeeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    EmployeeService employeeService = new EmployeeServiceImpl();
    ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();
    ObservableList<String> positions = FXCollections.observableArrayList(
            "Store Manager",
            "Cashier",
            "Stock Keeper",
            "Sales Associate",
            "Supervisor",
            "Security"
    );

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPosition;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private AnchorPane employeePane;

    @FXML
    private TableView<EmployeeDTO> tblEmployees;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtSearch;

    private EmployeeDTO getCurrentCustomer(){
        Long id = Long.valueOf(txtEmployeeId.getText());
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String position = cmbPosition.getValue();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        return new EmployeeDTO(id,firstName,lastName,position,contact,email);
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmployeeId.setText("Auto-Generated");
        txtFirstName.setText("");
        txtLastName.setText("");
        cmbPosition.setValue("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (employeeService.deleteEmployee(getCurrentCustomer())){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Deleted!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (employeeService.addEmployee(getCurrentCustomer())){
            new Alert(Alert.AlertType.CONFIRMATION,"New Employee added!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (employeeService.updateEmployee(getCurrentCustomer())){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee details updated!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbPosition.setItems(positions);

        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtEmployeeId.setText(String.valueOf(newValue.getEmployeeId()));
                txtFirstName.setText(newValue.getFirstName());
                txtLastName.setText(newValue.getLastName());
                cmbPosition.setValue(newValue.getPosition());
                txtContact.setText(newValue.getContactNumber());
                txtEmail.setText(newValue.getEmail());
            }
        });
    }

    public void loadTable(){
        employeeDTOS.clear();
        employeeDTOS = employeeService.getAllEmployees();
        if (employeeDTOS!=null) {
            tblEmployees.setItems(employeeDTOS);
        }else{
            new Alert(Alert.AlertType.ERROR,"Employee details are empty!").show();
        }
    }

    public void searchOnAction() {
        EmployeeDTO employeeDTO = employeeService.searchEmployee(Long.valueOf(txtSearch.getText()));
        if (employeeDTO!=null){
            txtEmployeeId.setText(String.valueOf(employeeDTO.getEmployeeId()));
            txtFirstName.setText(employeeDTO.getFirstName());
            txtLastName.setText(employeeDTO.getLastName());
            cmbPosition.setValue(employeeDTO.getPosition());
            txtContact.setText(employeeDTO.getContactNumber());
            txtEmail.setText(employeeDTO.getEmail());
        }
    }
}
