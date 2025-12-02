package edu.icet.clothify.repository;

import edu.icet.clothify.model.entity.Order;
import edu.icet.clothify.model.entity.OrderDetail;
import edu.icet.clothify.model.entity.Product;

import java.util.List;

public interface PosRepository {
    List<Product> getAllItems();
    boolean saveOrder(Order order);
    boolean saveDetails(OrderDetail orderDetail);
    void saveTransaction();
    void updateSession(Product product);
}
