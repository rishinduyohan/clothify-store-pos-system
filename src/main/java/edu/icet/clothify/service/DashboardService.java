package edu.icet.clothify.service;

import edu.icet.clothify.model.dto.OrderTM;
import javafx.collections.ObservableList;

import java.util.List;

public interface DashboardService {
    double getTotalRevenue();
    int getActiveOrders();
    int getTotalProducts();
    int getSoldItemCount();
    List<Object[]> getWeeklySalesData();
    List<OrderTM> getRecentOrders();
}
