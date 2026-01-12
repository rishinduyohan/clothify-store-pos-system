package edu.icet.clothify.service.impl;

import edu.icet.clothify.model.dto.OrderTM;
import edu.icet.clothify.repository.DashboardRepository;
import edu.icet.clothify.repository.impl.DashboardRepositoryImpl;
import edu.icet.clothify.service.DashboardService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    DashboardRepository dashboardRepository = new DashboardRepositoryImpl();
    LocalDateTime startDate = LocalDate.now().minusDays(10).atStartOfDay();
    ObservableList<OrderTM> recentOrders = FXCollections.observableArrayList();
    List<OrderTM> tmList = new ArrayList<>();

    @Override
    public double getTotalRevenue() {
       try{
           return dashboardRepository.getTotalRevenue();
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }
       return 0.0;
    }

    @Override
    public int getActiveOrders() {
        try{
            return dashboardRepository.getActiveOrders().intValue();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return 0;
    }

    @Override
    public int getTotalProducts() {
        try{
            return dashboardRepository.getTotalProducts().intValue();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return 0;
    }

    @Override
    public int getSoldItemCount() {
        try{
           return dashboardRepository.getSoldItemCount().intValue();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return 0;
    }

    @Override
    public List<Object[]> getWeeklySalesData() {
        try{
            return dashboardRepository.getWeeklySalesData(startDate);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return new ArrayList<>();
    }
    @Override
    public List<OrderTM> getRecentOrders() {
        try {
            List<Object[]> list = dashboardRepository.getRecentOrders();
            tmList.clear();
            if (list != null) {
                addRecentData(list);
            }
            return tmList;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return new ArrayList<>();
        }
    }

    private void addRecentData(List<Object[]> list){
        for (Object[] row : list) {
            String id = (row.length > 0 && row[0] != null) ? row[0].toString() : "";
            String customer = (row.length > 1 && row[1] != null) ? row[1].toString() : "";
            String status = (row.length > 2 && row[2] != null) ? row[2].toString() : "";
            double total = 0.0;
            if (row.length > 3 && row[3] != null) {
                try {
                    total = Double.parseDouble(row[3].toString());
                } catch (NumberFormatException _){}
            }
            String date = (row.length > 4 && row[4] != null) ? row[4].toString() : "";
            tmList.add(new OrderTM(id, customer, status, total, date));
        }
    }
}
