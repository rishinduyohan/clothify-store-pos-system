package edu.icet.clothify.controller;

import edu.icet.clothify.model.dto.ProductDTO;
import edu.icet.clothify.service.PosService;
import edu.icet.clothify.service.impl.PosServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PosController implements Initializable {

    PosService posService = new PosServiceImpl();

    @FXML
    private Button btnCatAcc;

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
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductsToGrid();
    }

    private void loadProductsToGrid() {
        productGrid.getChildren().clear();
        List<ProductDTO> products = posService.getALlItems();
        int column = 0;
        int row = 0;

        try {
            for (ProductDTO product : products) {
                URL fxmlUrl = getClass().getResource("/view/product_cart.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

                AnchorPane productCard = fxmlLoader.load();

                ImageView imageView = (ImageView) productCard.lookup("#image");
                Label nameLabel = (Label) productCard.lookup("#lblProductName");
                Label descriptionLabel = (Label) productCard.lookup("#lblProductDescription");
                Label categoryLabel = (Label) productCard.lookup("#lblCategory");
                Label priceLabel = (Label) productCard.lookup("#lblPrice");

                if (nameLabel != null) {
                    String imagePath = product.getImagePath();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        try {
                            Image image = new Image(imagePath);
                            imageView.setImage(image);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    nameLabel.setText(product.getName());
                    descriptionLabel.setText(product.getDescription());
                    priceLabel.setText("LKR " + product.getPrice().toPlainString());

                    if (product.getCategory() != null) {
                        categoryLabel.setText(product.getCategory().getName());
                    }
                }
                productGrid.add(productCard, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
