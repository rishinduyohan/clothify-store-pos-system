package edu.icet.clothify.service;

import java.util.List;

public interface DashboardService {
    double getTotalRevenue();
    int getActiveOrders();
    int getTotalProducts();
    int getSoldItemCount();
    List<Object[]> getWeeklySalesData();
}
