package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.ProductDTO;
import javafx.collections.ObservableList;

public interface InventoryService {
    ObservableList<String> getCategoryNames();
    ObservableList<String> getSupplierNames();
    ObservableList<String> getSizes();
    ObservableList<ProductDTO> getAllProducts();
    boolean addProduct(ProductDTO productDTO);
}
