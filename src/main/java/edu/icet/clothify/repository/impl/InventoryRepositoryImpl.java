package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.model.entity.Supplier;
import edu.icet.clothify.repository.InventoryRepository;
import org.hibernate.Session;

import java.util.List;

public class InventoryRepositoryImpl implements InventoryRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Category> getCategories() {
        return session.createQuery("From Category",Category.class).list();
    }

    @Override
    public List<Supplier> getSuppliers() {
        return session.createQuery("From Supplier",Supplier.class).list();
    }

    @Override
    public List<Product> getAllProducts() {
        return session.createQuery("From Product",Product.class).list();
    }
}
