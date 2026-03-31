package edu.icet.clothify.controller;

import edu.icet.clothify.service.PosService;
import edu.icet.clothify.service.impl.PosServiceImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Label lblDate;

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (posService.SaveOrder()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order placed!").show();
        }
        posService.clearCart();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        applyFilters();
    }

    private String currentCategory = "All Items";

    private void applyFilters() {
        String searchText = searchField.getText();
        posService.filterProducts(searchText, currentCategory, productGrid, cartContainer, lblTotal);
    }

    private void setCategoryFilter(String category, Button activeBtn) {
        currentCategory = category;

        Button[] btns = { btnCatAll, btnCatMen, btnCatWomen, btnCatShoes, btnCatAcc };
        for (Button btn : btns) {
            if (btn != null) {
                if (btn == activeBtn) {
                    btn.setStyle("-fx-background-color: #4B7BEC; -fx-text-fill: white; -fx-background-radius: 20;");
                } else {
                    btn.setStyle(
                            "-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 20; -fx-border-color: #d1d8e0; -fx-border-radius: 20;");
                }
            }
        }
        applyFilters();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posService.loadProductsToGrid(productGrid, cartContainer, lblTotal);

        if (searchField != null) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                applyFilters();
            });
        }

        if (btnCatAll != null)
            btnCatAll.setOnAction(e -> setCategoryFilter("All Items", btnCatAll));
        if (btnCatMen != null)
            btnCatMen.setOnAction(e -> setCategoryFilter("Gents Wear", btnCatMen));
        if (btnCatWomen != null)
            btnCatWomen.setOnAction(e -> setCategoryFilter("Ladies Wear", btnCatWomen));
        if (btnCatShoes != null)
            btnCatShoes.setOnAction(e -> setCategoryFilter("Footwear", btnCatShoes));
        if (btnCatAcc != null)
            btnCatAcc.setOnAction(e -> setCategoryFilter("Accessories", btnCatAcc));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy | hh:mm:ss a");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void btnClearAllOnAction() {
        if (posService.clearCart()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item removed!").show();
        }
    }
}
