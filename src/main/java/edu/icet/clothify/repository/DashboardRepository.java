package edu.icet.clothify.repository;

public interface DashboardRepository {
    double getTotalRevenue();
    Long getActiveOrders();
    Long getTotalProducts();
    Long getSoldItemCount();
}
