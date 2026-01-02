package edu.icet.clothify.controller;

import edu.icet.clothify.service.ReportGenarateService;
import edu.icet.clothify.service.impl.ReportGenarateServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class MonthlyReportController implements Initializable {
    ReportGenarateService reportGenarateService = new ReportGenarateServiceImpl();
    ObservableList<Integer> years = FXCollections.observableArrayList();
    ObservableList<String> months = FXCollections.observableArrayList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnGenerate;

    @FXML
    private ComboBox<String> cmbMonth;

    @FXML
    private ComboBox<Integer> cmbYear;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {
        try {
            if (reportGenarateService.monthlySalesReport(getMonthValue(cmbMonth.getValue()), cmbYear.getValue())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Report Generated successfully!").show();
            }
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Month or Year must selected!").show();
        }
    }

    private int getMonthValue(String month){
        for(int i = 0; i <= months.size(); i++){
            if (month.equalsIgnoreCase(months.get(i))){
                return i+1;
            }
        }
        return 0;
    }

    private void loadMonths(){
        cmbMonth.setItems(months);
        cmbMonth.getSelectionModel().selectFirst();
    }

    private void loadYears(){
        int currentYear = Year.now().getValue();

        for(int i = currentYear-5; i<= currentYear; i++){
            years.add(i);
        }
        cmbYear.setItems(years);
        cmbYear.getSelectionModel().select(currentYear);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMonths();
        loadYears();
    }
}

