package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.repository.DashboardRepository;
import org.hibernate.Session;

public class DashboardRepositoryImpl implements DashboardRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public double getTotalRevenue() {
        return session.createQuery("SELECT SUM(totalAmount) FROM Order", Double.class).uniqueResult();
    }

    @Override
    public Long getActiveOrders() {
        return session.createQuery("SELECT COUNT(orderId) FROM Order",Long.class).uniqueResult();
    }

    @Override
    public Long getTotalProducts() {
        return session.createQuery("SELECT COUNT(productId) FROM Product",Long.class).uniqueResult();
    }

    @Override
    public Long getSoldItemCount() {
        return session.createQuery("SELECT SUM(qty) FROM OrderDetail",Long.class).uniqueResult();
    }
}
