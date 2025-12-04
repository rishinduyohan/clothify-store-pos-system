package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.ProductDTO;
import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.model.entity.Supplier;
import edu.icet.clothify.repository.InventoryRepository;
import edu.icet.clothify.repository.impl.InventoryRepositoryImpl;
import edu.icet.clothify.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryServiceImpl implements InventoryService {
    InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
    ObservableList<String> categories = FXCollections.observableArrayList();
    ObservableList<String> suppliers = FXCollections.observableArrayList();
    ObservableList<ProductDTO> list = FXCollections.observableArrayList();


    @Override
    public ObservableList<String> getCategoryNames() {
       for (Category category: inventoryRepository.getCategories()){
           categories.add(category.getName());
       }
       return categories;
    }

    @Override
    public ObservableList<String> getSupplierNames() {
        for (Supplier supplier: inventoryRepository.getSuppliers()){
            suppliers.add(supplier.getCompanyName());
        }
        return suppliers;
    }

    @Override
    public ObservableList<String> getSizes() {
        return FXCollections.observableArrayList(
                "XXXL",
                "XXL",
                "XL",
                "Large",
                "Medium",
                "Small",
                "Kids"
        );
    }

    @Override
    public ObservableList<ProductDTO> getAllProducts() {
        list.clear();
        if (inventoryRepository.getAllProducts()!=null) {
            for (Product product : inventoryRepository.getAllProducts()) {
                list.add(new ProductDTO(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImagePath(),
                        product.getPrice(),
                        product.getStockQuantity(),
                        product.getCategory().getName(),
                        product.getSupplier().getCompanyName()
                ));
            }
        }
        return list;
    }
}
