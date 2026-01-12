package edu.icet.clothify.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface DashboardRepository {
    double getTotalRevenue();
    Long getActiveOrders();
    Long getTotalProducts();
    Long getSoldItemCount();
    List<Object[]> getWeeklySalesData(LocalDateTime startDate);
    List<Object[]> getRecentOrders();
}
