package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Category;

import java.util.List;

public interface InventoryRepository {
    List<Category> getCategories();
}
