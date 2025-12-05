package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.model.entity.Supplier;

import java.util.List;

public interface InventoryRepository {
    List<Category> getCategories();
    List<Supplier> getSuppliers();
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    Category getCategory(String name);
    Supplier getSupplier(String company);
}
