package edu.icet.clothify.service;

public interface DashboardService {
    double getTotalRevenue();
    int getActiveOrders();
    int getTotalProducts();
    int getSoldItemCount();
}
