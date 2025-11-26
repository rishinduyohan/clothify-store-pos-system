package edu.icet.ecom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    Stage stage = new Stage();
    private final String ACTIVE_STYLE = "-fx-background-color: #4B7BEC; -fx-background-radius: 0 30 30 0; -fx-cursor: hand;-fx-text-fill:white;";
    private final String INACTIVE_STYLE = "-fx-background-color: transparent; -fx-cursor: hand;";

    @FXML
    private AnchorPane mainContent;

    @FXML
    private AnchorPane middleContent;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnPos;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnViewAllOrders;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItems;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Circle imgUserProfile;

    @FXML
    private Label lblActiveOrders;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemsSold;

    @FXML
    private Label lblMonthlyGoalPercent;

    @FXML
    private Label lblMonthlyGoalValue;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblTotalRevenue;

    @FXML
    private Label lblTrend1Name;

    @FXML
    private Label lblTrend1Sold;

    @FXML
    private Label lblTrend2Name;

    @FXML
    private Label lblTrend2Sold;

    @FXML
    private Label lblTrend3Name;

    @FXML
    private Label lblTrend3Sold;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserRole;

    @FXML
    private VBox leftPane;

    @FXML
    private ScrollPane middlePane;

    @FXML
    private Circle notifIndicator;

    @FXML
    private ProgressBar progressMonthlyGoal;

    @FXML
    private BarChart<String, Number> revenueChart;

    @FXML
    private VBox rightSidePane;

    @FXML
    private TableView<?> tblRecentOrders;

    @FXML
    private VBox vboxCashiers;

    @FXML
    private VBox vboxNotifications;

    @FXML
    private VBox vboxTrending;

    @FXML
    void btnCustomersOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnPosOnAction(ActionEvent event) {
        updateActiveButton(btnPos);
        setUi("/view/pos_dashboard.fxml");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewAllOrdersOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Revenue");

        series.getData().add(new XYChart.Data<>("Mon", 1200));
        series.getData().add(new XYChart.Data<>("Tue", 1500));
        series.getData().add(new XYChart.Data<>("Wed", 800));
        series.getData().add(new XYChart.Data<>("Thu", 2100));
        series.getData().add(new XYChart.Data<>("Fri", 1750));
        series.getData().add(new XYChart.Data<>("Sat", 3200));
        series.getData().add(new XYChart.Data<>("Sun", 2900));

        revenueChart.getData().add(series);
    }

    private void updateActiveButton(Button clickedButton) {
        List<Button> allButtons = Arrays.asList(btnDashboard, btnPos, btnEmployee, btnInventory, btnCustomers,btnReports);

        for (Button btn : allButtons) {
            if (btn == clickedButton) {
                btn.setStyle(ACTIVE_STYLE);
            } else {
                btn.setStyle(INACTIVE_STYLE);
            }
        }
    }
    private void setUi(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            Parent loadedUi = loader.load();

            middleContent.getChildren().clear();
            middleContent.getChildren().add(loadedUi);

            AnchorPane.setLeftAnchor(loadedUi, 0.0);
            AnchorPane.setRightAnchor(loadedUi, 0.0);
            AnchorPane.setTopAnchor(loadedUi, 0.0);
            AnchorPane.setBottomAnchor(loadedUi, 0.0);

        } catch (IOException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
