package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.Product;
import edu.icet.clothify.repository.PosRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PosRepositoryImpl implements PosRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory(Product.class);
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    @Override
    public List<Product> getAllItems() {
        try {
            return session.createQuery("FROM Product", Product.class).list();
        } finally {
            session.close();
        }
    }
}
