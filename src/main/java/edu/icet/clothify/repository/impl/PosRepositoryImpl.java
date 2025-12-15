package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.model.entity.*;
import edu.icet.clothify.repository.PosRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class PosRepositoryImpl implements PosRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    @Override
    public List<Product> getAllItems() {
        return session.createQuery("FROM Product", Product.class).list();
    }

    @Override
    public boolean saveOrder(Order order) {
        transaction = session.beginTransaction();
        session.persist(order);
        return true;
    }

    @Override
    public boolean saveDetails(OrderDetail orderDetail) {
        session.persist(orderDetail);
        return true;
    }
    @Override
    public void saveTransaction() {
        transaction.commit();
    }

    @Override
    public void updateSession(Product product) {
        session.merge(product);
    }
}
