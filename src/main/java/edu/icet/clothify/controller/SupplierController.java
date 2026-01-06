package edu.icet.clothify.controller;

import edu.icet.clothify.model.dto.SupplierDTO;
import edu.icet.clothify.service.SupplierService;
import edu.icet.clothify.service.impl.SupplierServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    SupplierService supplierService = new SupplierServiceImpl();
    ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colItems;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private Label lblActiveSuppliers;

    @FXML
    private Label lblPendingOrders;

    @FXML
    private TableView<SupplierDTO> tblSuppliers;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtContactPerson;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSearch;

    private SupplierDTO getCurrentSupplier(){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setCompanyName(txtCompany.getText());
        supplierDTO.setContactPerson(txtContactPerson.getText());
        supplierDTO.setContactNumber(txtPhone.getText());
        supplierDTO.setItemCategory(txtCategory.getText());
        supplierDTO.setEmail(txtEmail.getText());
        return supplierDTO;
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        if(supplierService.addSupplier(getCurrentSupplier())){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier added Successfully!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtCompany.setText("");
        txtCategory.setText("");
        txtPhone.setText("");
        txtContactPerson.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(supplierService.removeSupplier(Long.valueOf(txtId.getText()),getCurrentSupplier())){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted Successfully!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if(supplierService.updateSupplier(Long.valueOf(txtId.getText()),getCurrentSupplier())){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated Successfully!").show();
            btnClearOnAction(event);
        }
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        loadTable();
        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtId.setText(String.valueOf(newValue.getSupplierId()));
                txtCompany.setText(newValue.getCompanyName());
                txtContactPerson.setText(newValue.getContactPerson());
                txtEmail.setText(newValue.getEmail());
                txtPhone.setText(newValue.getContactNumber());
                txtCategory.setText(newValue.getItemCategory());
            }
        });
    }

    private void loadTable() {
        supplierDTOS.clear();
        supplierDTOS = supplierService.getAllSuppliers();
        if (supplierDTOS!=null) {
            tblSuppliers.setItems(supplierDTOS);
        }else{
            new Alert(Alert.AlertType.ERROR,"Supplier details are empty!").show();
        }
    }
}
