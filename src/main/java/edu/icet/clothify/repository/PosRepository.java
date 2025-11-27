package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Product;

import java.util.List;

public interface PosRepository {

    List<Product> getAllItems();
}
