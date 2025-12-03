package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.repository.InventoryRepository;
import edu.icet.clothify.repository.impl.InventoryRepositoryImpl;
import edu.icet.clothify.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryServiceImpl implements InventoryService {
    InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
    ObservableList<String> categories = FXCollections.observableArrayList();

    @Override
    public ObservableList<String> getCategoryNames() {
       for (Category category: inventoryRepository.getCategories()){
           categories.add(category.getName());
       }
       return categories;
    }
}
