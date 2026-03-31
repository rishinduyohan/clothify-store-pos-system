package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.OrderTM;

import java.util.List;

public interface DashboardService {
    double getTotalRevenue();

    int getActiveOrders();

    int getTotalProducts();

    int getSoldItemCount();

    List<Object[]> getWeeklySalesData();

    List<OrderTM> getRecentOrders();

    double getMonthlyTarget();

    void updateMonthlyTarget(double target);
}
