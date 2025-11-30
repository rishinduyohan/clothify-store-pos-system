package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.ProductDTO;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public interface PosService {

    List<ProductDTO> getALlItems();
    void loadProductsToGrid(GridPane productGrid, VBox cartContainer, Label lblTotal);
    boolean clearCart();
}
