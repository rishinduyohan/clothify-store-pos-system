package edu.icet.clothify.controller;

import edu.icet.clothify.service.PosService;
import edu.icet.clothify.service.impl.PosServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PosController implements Initializable {

    PosService posService = new PosServiceImpl();

    @FXML
    private Button btnCatAcc;

    @FXML
    private VBox cartContainer;

    @FXML
    private Button btnCatAll;

    @FXML
    private Button btnCatMen;

    @FXML
    private Button btnCatShoes;

    @FXML
    private Button btnCatWomen;

    @FXML
    private Button btnPay;

    @FXML
    private TableView<?> cartTable;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblDiscount;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTax;

    @FXML
    private Label lblTotal;

    @FXML
    private GridPane productGrid;

    @FXML
    private TextField searchField;

    @FXML
    public Button btnPlaceOrder;


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (posService.SaveOrder()){
            new Alert(Alert.AlertType.CONFIRMATION, "Order placed!").show();
        }
        posService.clearCart();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        //text search
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posService.loadProductsToGrid(productGrid,cartContainer,lblSubtotal);
    }

    public void btnClearAllOnAction() {
        if (posService.clearCart()){
            new Alert(Alert.AlertType.CONFIRMATION, "Item removed!").show();
        }
    }
}
