package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.CartItemDTO;
import edu.icet.clothify.model.dto.ProductDTO;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.repository.PosRepository;
import edu.icet.clothify.repository.impl.PosRepositoryImpl;
import edu.icet.clothify.service.PosService;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PosServiceImpl implements PosService {

    PosRepository posRepository = new PosRepositoryImpl();
    List<ProductDTO> list = new ArrayList<>();
    private List<CartItemDTO> cartItems = new ArrayList<>();
    Label lblTotal = null;

    @Override
    public List<ProductDTO> getALlItems() {
        list.clear();
        if (posRepository.getAllItems()!=null) {
            for (Product product : posRepository.getAllItems()) {
                list.add(new ProductDTO(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImagePath(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getCategory(),
                        product.getSupplier()
                ));
            }
        }
        return list;
    }

    @Override
    public void loadProductsToGrid(GridPane productGrid, VBox cartContainer, Label lblTotal) {
        productGrid.getChildren().clear();
        List<ProductDTO> products = getALlItems();
        int column = 0;
        int row = 0;
        this.lblTotal = lblTotal;

        try {
            if(products!=null) {
                for (ProductDTO product : products) {
                    URL fxmlUrl = getClass().getResource("/view/product_cart.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

                    AnchorPane productCard = fxmlLoader.load();

                    ImageView imageView = (ImageView) productCard.lookup("#image");
                    Label nameLabel = (Label) productCard.lookup("#lblProductName");
                    Label descriptionLabel = (Label) productCard.lookup("#lblProductDescription");
                    Label categoryLabel = (Label) productCard.lookup("#lblCategory");
                    Label priceLabel = (Label) productCard.lookup("#lblPrice");
                    Button addToCartBtn = (Button) productCard.lookup("#btnAddToCart");

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
                addToCartBtn.setOnAction(event -> {
                    addToCart(product,cartContainer);
                });
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCart(ProductDTO product,VBox cartContainer) {
        Optional<CartItemDTO> existingItem = cartItems.stream()
                .filter(item -> item.getProduct().getProductId().equals(product.getProductId()))
                .findFirst();

        if (existingItem.isPresent()){
            CartItemDTO item = existingItem.get();
            item.setQuantity(item.getQuantity()+1);
            item.calculateTotal();
        }else{
            cartItems.add(new CartItemDTO(product,1));
        }
        updateCartUi(cartContainer);
    }

    public void updateCartUi(VBox cartContainer) {
        cartContainer.getChildren().clear();
        BigDecimal netTotal = BigDecimal.ZERO;

        for (CartItemDTO item : cartItems) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cart_row.fxml"));
                HBox row = loader.load();

                Label lblName = (Label) row.lookup("#lblName");
                Label lblQty = (Label) row.lookup("#lblQty");
                Label lblTotal = (Label) row.lookup("#lblTotal");
                Button btnRemove = (Button) row.lookup("#btnRemove");

                lblName.setText(item.getProduct().getName());
                lblQty.setText(item.getQuantity() + " x " + item.getProduct().getPrice());
                lblTotal.setText(item.getTotal()+"");

                btnRemove.setOnAction(e -> {
                    cartItems.remove(item);
                    updateCartUi(cartContainer);
                });
                cartContainer.getChildren().add(row);

                netTotal = netTotal.add(item.getTotal());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lblTotal.setText(netTotal.toPlainString());
    }
}
