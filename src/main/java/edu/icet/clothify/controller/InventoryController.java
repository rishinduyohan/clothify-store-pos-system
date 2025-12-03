package edu.icet.clothify.controller;

import edu.icet.clothify.service.InventoryService;
import edu.icet.clothify.service.impl.InventoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> sizes = FXCollections.observableArrayList();
    ObservableList<String> suppliers = FXCollections.observableArrayList();
    InventoryService inventoryService = new InventoryServiceImpl();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBrowseImage;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private ComboBox<String> cmbSupplier;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblLowStock;

    @FXML
    private Label lblTotalItems;

    @FXML
    private TableView<?> tblInventory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAccessories(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnAllCategories(ActionEvent event) {

    }

    @FXML
    void btnBrowseImageOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnKids(ActionEvent event) {

    }

    @FXML
    void btnMenWear(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnWomentsWear(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.setItems(inventoryService.getCategoryNames());
    }
}
