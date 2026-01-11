package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.repository.DashboardRepository;
import org.hibernate.Session;


import java.time.LocalDateTime;
import java.util.List;

public class DashboardRepositoryImpl implements DashboardRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();
    String weeklyResultQuery = "SELECT cast(o.date as date), SUM(o.totalAmount) " +
            "FROM Order o " +
            "WHERE o.date >= :startDate " +
            "GROUP BY cast(o.date as date) " +
            "ORDER BY cast(o.date as date) ASC";

    @Override
    public double getTotalRevenue() {
        return session.createQuery("SELECT SUM(totalAmount) FROM Order", Double.class).uniqueResult();
    }

    @Override
    public Long getActiveOrders() {
        return session.createQuery("SELECT COUNT(orderId) FROM Order", Long.class).uniqueResult();
    }

    @Override
    public Long getTotalProducts() {
        return session.createQuery("SELECT COUNT(productId) FROM Product", Long.class).uniqueResult();
    }

    @Override
    public Long getSoldItemCount() {
        return session.createQuery("SELECT SUM(qty) FROM OrderDetail", Long.class).uniqueResult();
    }

    @Override
    public List<Object[]> getWeeklySalesData(LocalDateTime startDate) {
        return session.createQuery(weeklyResultQuery,Object[].class).setParameter("startDate",startDate).getResultList();
    }
}
