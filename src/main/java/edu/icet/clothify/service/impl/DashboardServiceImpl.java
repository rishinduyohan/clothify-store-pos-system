package edu.icet.clothify.service.impl;

import edu.icet.clothify.repository.DashboardRepository;
import edu.icet.clothify.repository.impl.DashboardRepositoryImpl;
import edu.icet.clothify.service.DashboardService;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    DashboardRepository dashboardRepository = new DashboardRepositoryImpl();
    LocalDateTime startDate = LocalDate.now().minusDays(10).atStartOfDay();

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
}
