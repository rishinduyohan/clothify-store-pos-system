package edu.icet.clothify.controller;

import edu.icet.clothify.service.ReportGenarateService;
import edu.icet.clothify.service.impl.ReportGenarateServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class DatePickerController {
    ReportGenarateService reportGenarateService = new ReportGenarateServiceImpl();
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnGenerate;

    @FXML
    private DatePicker datePicker;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {
        try {
            if (reportGenarateService.dailySalesReport(datePicker.getValue().toString())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Report Generated successfully!").show();
            }
        }catch (NullPointerException _){
            new Alert(Alert.AlertType.ERROR, "Date must be NOT NULL!").show();
        }
    }

}
