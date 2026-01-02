package edu.icet.clothify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController {
    Stage stage = new Stage();
    @FXML
    private Button btnDailySales;

    @FXML
    private Button btnMonthlyReport;

    @FXML
    private Button btnSupplierReport;

    @FXML
    private Button btnTopSelling;

    @FXML
    private VBox cardDailySales;

    @FXML
    private VBox cardMonthlyRevenue;

    @FXML
    private VBox cardSupplierList;

    @FXML
    private VBox cardTopSelling;

    @FXML
    void btnDailySalesOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/daily_reports.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Daily reports");
        stage.show();
    }

    @FXML
    void btnMonthlyReportOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/monthly_revenue.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Monthly reports");
        stage.show();
    }

    @FXML
    void btnSupplierReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnTopSellingOnAction(ActionEvent event) {

    }

}
