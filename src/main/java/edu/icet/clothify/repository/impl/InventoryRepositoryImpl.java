package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Category;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.model.entity.Supplier;
import edu.icet.clothify.repository.InventoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    @Override
    public boolean addProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.merge(product);
        transaction.commit();
        return true;
    }

    @Override
    public boolean removeProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.remove(product);
        transaction.commit();
        return true;
    }

    @Override
    public Category getCategory(String name) {
        return session.createQuery("From Category where name = '"+name+"'",Category.class).uniqueResult();
    }

    @Override
    public Supplier getSupplier(String company) {
        return session.createQuery("From Supplier where companyName = '"+company+"'",Supplier.class).uniqueResult();
    }

}
