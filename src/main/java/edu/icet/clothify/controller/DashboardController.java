package edu.icet.clothify.controller;

import edu.icet.clothify.config.UserSession;
import edu.icet.clothify.model.dto.OrderTM;
import edu.icet.clothify.model.dto.UserDTO;
import edu.icet.clothify.service.DashboardService;
import edu.icet.clothify.service.impl.DashboardServiceImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    Stage stage = new Stage();
    double totalRevenue = 0.0;
    UserDTO loggedUser = new UserDTO();
    private static final String ACTIVE_STYLE = "-fx-background-color: #4B7BEC; -fx-background-radius: 0 30 30 0; -fx-cursor: hand;-fx-text-fill:white;";
    private static final String INACTIVE_STYLE = "-fx-background-color: transparent; -fx-cursor: hand;";
    DashboardService dashboardService = new DashboardServiceImpl();

    @FXML
    private AnchorPane mainContent;

    @FXML
    private AnchorPane middleContent;

    @FXML
    private Button btnSupplier;

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
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colMethod;

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
    private Label lblTotalProducts;

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
    private TableView<OrderTM> tblRecentOrders;

    @FXML
    private VBox vboxCashiers;

    @FXML
    private VBox vboxNotifications;

    @FXML
    private VBox vboxTrending;

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        updateActiveButton(btnSupplier);
        setUi("/view/Supplier.fxml");
    }

    @FXML
    void btnViewAllOrdersOnAction(ActionEvent event) {
        //view orders
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        updateActiveButton(btnEmployee);
        setUi("/view/employee.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        updateActiveButton(btnInventory);
        setUi("/view/inventory_mgt.fxml");
    }

    @FXML
    void btnPosOnAction(ActionEvent event) {
        updateActiveButton(btnPos);
        setUi("/view/pos_dashboard.fxml");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        updateActiveButton(btnReports);
        setUi("/view/report_genarate.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggedUser = UserSession.getInstance().getLoggedUser();
        loadRoles(loggedUser);
        loadSummary();
        loadWeeklyRevenueChart();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy | hh:mm:ss a");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        ObservableList<OrderTM> orders = FXCollections.observableArrayList(dashboardService.getRecentOrders());
        tblRecentOrders.setItems(orders);
    }

    private void loadRecentOrders(){
    }
    private void loadSummary(){
        totalRevenue = dashboardService.getTotalRevenue();
        lblTotalRevenue.setText("LKR "+totalRevenue);
        lblActiveOrders.setText(""+dashboardService.getActiveOrders());
        lblTotalProducts.setText(""+dashboardService.getTotalProducts());
        lblItemsSold.setText(""+dashboardService.getSoldItemCount());

        double goal = 1200000.0;
        progressMonthlyGoal.setProgress(totalRevenue / goal);
        lblMonthlyGoalPercent.setText((int)((totalRevenue / goal) * 100) + "%");
    }
    private void loadWeeklyRevenueChart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weekly Revenue");
        List<Object[]> weeklyData = dashboardService.getWeeklySalesData();

        for (Object[] row : weeklyData) {
            String date = row[0].toString();
            Double total = (Double) row[1];
            series.getData().add(new XYChart.Data<>(date, total));
        }
        revenueChart.getData().clear();
        revenueChart.getData().add(series);
    }
    private void loadRoles(UserDTO userDTO){
        if (userDTO!=null){
            if (!userDTO.getEmail().endsWith("@clothify.com")){
                btnEmployee.setDisable(true);
                btnInventory.setDisable(true);
                btnReports.setDisable(true);
                btnSupplier.setDisable(true);
                btnEmployee.setVisible(false);
                btnInventory.setVisible(false);
                btnReports.setVisible(false);
                btnSupplier.setVisible(false);
            }
            lblUserName.setText(userDTO.getUsername());
            lblUserRole.setText(userDTO.getEmail());
            try {
                String imagePath = userDTO.getImage();
                if (imagePath != null && !imagePath.isEmpty()) {
                    Image profileImg = new Image(imagePath, true);
                    //for load image to circle
                    profileImg.progressProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.doubleValue() == 1.0) { // when 100% complete
                            Platform.runLater(() -> {
                                ImagePattern pattern = new ImagePattern(profileImg);
                                imgUserProfile.setFill(pattern);
                            });
                        }
                    });
                }
            } catch (Exception e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }
    private void updateActiveButton(Button clickedButton) {
        List<Button> allButtons = Arrays.asList(btnDashboard, btnPos, btnEmployee, btnInventory, btnSupplier,btnReports);

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
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
