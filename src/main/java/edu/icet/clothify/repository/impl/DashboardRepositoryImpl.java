package edu.icet.clothify.repository.impl;

import edu.icet.clothify.config.HibernateUtil;
import edu.icet.clothify.repository.DashboardRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.icet.clothify.model.entity.AppSetting;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardRepositoryImpl implements DashboardRepository {
    Session session = HibernateUtil.getSessionFactory().openSession();

    String weeklyResultQuery = "SELECT cast(o.date as date), SUM(o.totalAmount) " +
            "FROM Order o " +
            "WHERE o.date >= :startDate " +
            "GROUP BY cast(o.date as date) " +
            "ORDER BY cast(o.date as date) ASC";

    String recentOrders = "SELECT o.orderId, p.name, o.date, o.totalAmount, o.paymentMethod " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "JOIN od.product p " +
            "GROUP BY o.orderId, p.name, o.date, o.totalAmount, o.paymentMethod " +
            "ORDER BY o.date DESC";

    @Override
    public double getTotalRevenue() {
        session.clear();
        Double result = session.createQuery("SELECT SUM(totalAmount) FROM Order", Double.class).uniqueResult();
        return result != null ? result : 0.0;
    }

    @Override
    public Long getActiveOrders() {
        session.clear();
        Long result = session.createQuery("SELECT COUNT(orderId) FROM Order", Long.class).uniqueResult();
        return result != null ? result : 0L;
    }

    @Override
    public Long getTotalProducts() {
        session.clear();
        Long result = session.createQuery("SELECT COUNT(productId) FROM Product", Long.class).uniqueResult();
        return result != null ? result : 0L;
    }

    @Override
    public Long getSoldItemCount() {
        session.clear();
        Long result = session.createQuery("SELECT SUM(qty) FROM OrderDetail", Long.class).uniqueResult();
        return result != null ? result : 0L;
    }

    @Override
    public List<Object[]> getWeeklySalesData(LocalDateTime startDate) {
        session.clear();
        return session.createQuery(weeklyResultQuery, Object[].class).setParameter("startDate", startDate)
                .getResultList();
    }

    @Override
    public List<Object[]> getRecentOrders() {
        session.clear();
        return session.createQuery(recentOrders, Object[].class).setMaxResults(5).getResultList();
    }

    @Override
    public double getMonthlyTarget() {
        session.clear();
        AppSetting setting = session.find(AppSetting.class, "MONTHLY_TARGET");
        if (setting != null) {
            try {
                return Double.parseDouble(setting.getSettingValue());
            } catch (NumberFormatException e) {
                return 1200000.0;
            }
        }
        return 1200000.0; // default
    }

    @Override
    public void updateMonthlyTarget(double target) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AppSetting setting = session.find(AppSetting.class, "MONTHLY_TARGET");
            if (setting == null) {
                setting = new AppSetting("MONTHLY_TARGET", String.valueOf(target));
            } else {
                setting.setSettingValue(String.valueOf(target));
            }
            session.merge(setting);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }
}
