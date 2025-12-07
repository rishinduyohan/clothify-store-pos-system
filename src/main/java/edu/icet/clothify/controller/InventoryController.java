package edu.icet.clothify.controller;

import edu.icet.clothify.config.CloudinaryUtil;
import edu.icet.clothify.model.dto.ProductDTO;
import edu.icet.clothify.service.InventoryService;
import edu.icet.clothify.service.impl.InventoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> sizes = FXCollections.observableArrayList();
    ObservableList<String> suppliers = FXCollections.observableArrayList();
    ObservableList<ProductDTO> productDTOS = FXCollections.observableArrayList();
    InventoryService inventoryService = new InventoryServiceImpl();
    private String productImageUrl;
    ProductDTO productDTO;

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
    private TableColumn<?, ?> colSupplier;

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblLowStock;

    @FXML
    private Label lblTotalItems;

    @FXML
    private TableView<ProductDTO> tblInventory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    private ProductDTO getCurrentProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(txtName.getText());
        productDTO.setCategory(cmbCategory.getValue());
        productDTO.setDescription(cmbSize.getValue());
        String priceText = txtPrice.getText();
        String qtyText = txtQty.getText();

        if (productImageUrl != null) {
            productDTO.setImagePath(productImageUrl);
        } else {
            productDTO.setImagePath("https://via.placeholder.com/150");
        }

        if (priceText.isEmpty() && qtyText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a value!").show();
        }else {
            try {
                double price = Double.parseDouble(priceText);
                productDTO.setPrice(BigDecimal.valueOf(price));

                Integer qty = Integer.valueOf(qtyText);
                productDTO.setStockQuantity(qty);

            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid Price! Please enter a number.").show();
                clearText();
            }
        }
        productDTO.setSupplier(cmbSupplier.getValue());
        return productDTO;
    }

    private void clearText() {
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if(inventoryService.addProduct(getCurrentProduct())){
            new Alert(Alert.AlertType.CONFIRMATION,"Product added Successfully!").show();
        }
        loadTable();
    }
    @FXML
    void btnBrowseImageOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) btnBrowseImage.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            imgProduct.setImage(new Image(file.toURI().toString()));

            System.out.println("Uploading image... Please wait.");

            String url = CloudinaryUtil.uploadImage(file);

            if (url != null) {
                this.productImageUrl = url;
                System.out.println("Upload Success! URL: " + url);
            } else {
                System.out.println("Upload Failed!");
            }
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtName.setText("");
        txtPrice.setText("0.00");
        txtQty.setText("0");
        cmbCategory.setValue("Select category");
        cmbSize.setValue("Select size");
        cmbSupplier.setValue("Select supplier");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(inventoryService.removeProduct(productDTO.getProductId(),getCurrentProduct())){
            new Alert(Alert.AlertType.CONFIRMATION,"Product removed Successfully!").show();
        }
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if(inventoryService.updateProduct(productDTO.getProductId(),getCurrentProduct())){
            new Alert(Alert.AlertType.CONFIRMATION,"Product updated Successfully!").show();
        }
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.setItems(inventoryService.getCategoryNames());
        cmbSupplier.setItems(inventoryService.getSupplierNames());
        cmbSize.setItems(inventoryService.getSizes());

        colId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
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


    public void loadTable(){
        productDTOS.clear();
        productDTOS = inventoryService.getAllProducts();
        if (productDTOS!=null) {
            tblInventory.setItems(productDTOS);
        }else{
            new Alert(Alert.AlertType.ERROR,"Product details are empty!").show();
        }
    }
}
