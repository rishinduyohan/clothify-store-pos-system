package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
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
    private TableView<?> tblSuppliers;

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

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        loadTable();
        tblInventory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtName.setText(newValue.getName());
                cmbCategory.setValue(newValue.getCategory());
                cmbSize.setValue(newValue.getDescription());
                txtPrice.setText(String.valueOf(newValue.getPrice()));
                txtQty.setText(String.valueOf(newValue.getStockQuantity()));
                cmbSupplier.setValue(newValue.getSupplier());
                String imagePath = newValue.getImagePath();
                if (imagePath != null && !imagePath.isEmpty()) {
                    try {
                        Image image = new Image(imagePath);
                        imgProduct.setImage(image);
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR,"Product image are empty!").show();
                    }
                }
                productDTO = newValue;
            }
        });
    }
}
