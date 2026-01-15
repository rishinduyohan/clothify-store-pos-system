package edu.icet.clothify.controller;

import edu.icet.clothify.config.UserSession;
import edu.icet.clothify.model.dto.UserDTO;
import edu.icet.clothify.service.UserService;
import edu.icet.clothify.service.impl.UserServiceImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileCardController implements Initializable {

    UserDTO userDTO = new UserDTO();
    UserService userService = new UserServiceImpl();
    Stage stage = new Stage();

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnLogout;

    @FXML
    private Circle imgProfile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblRole;

    @FXML
    private AnchorPane profileAnchor;

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_page.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        stage.setTitle("Login Page");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/login.png")));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDTO = UserSession.getInstance().getLoggedUser();
        lblFullName.setText(userDTO.getUsername());
        lblEmail.setText(userDTO.getEmail());
        lblRole.setText(userService.checkRole(userDTO.getEmail()));

        try {
            String imagePath = userDTO.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                Image profileImg = new Image(imagePath, true);
                //for load image to circle
                profileImg.progressProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.doubleValue() == 1.0) { // when 100% complete
                        Platform.runLater(() -> {
                            ImagePattern pattern = new ImagePattern(profileImg);
                            imgProfile.setFill(pattern);
                        });
                    }
                });
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}

