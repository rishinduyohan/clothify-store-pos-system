package edu.icet.clothify.controller;

import edu.icet.clothify.config.CloudinaryUtil;
import edu.icet.clothify.model.dto.UserDTO;
import edu.icet.clothify.service.UserService;
import edu.icet.clothify.service.impl.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;

public class LoginController {
    UserService userService = new UserServiceImpl();
    private boolean isLoginView = true;
    private File selectedImageFile;
    String imageUrl = "";
    String password = "";
    String confirmPassword = "";

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox chkRememberMe;

    @FXML
    private Circle imgSignupPreview;

    @FXML
    private Label lblFooterText;

    @FXML
    private Label lblTitle;

    @FXML
    private Hyperlink lnkForgot;

    @FXML
    private Hyperlink lnkToggle;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TextField txtLoginEmail;

    @FXML
    private PasswordField txtLoginPassword;

    @FXML
    private PasswordField txtSignupConfirmPassword;

    @FXML
    private TextField txtSignupEmail;

    @FXML
    private TextField txtSignupName;

    @FXML
    private PasswordField txtSignupPassword;

    @FXML
    private VBox vboxLogin;

    @FXML
    private VBox vboxSignup;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtLoginEmail.getText();
        String inputPassword = txtLoginPassword.getText();

        UserDTO user = userService.getUser(email);

        if (user != null) {
            if (BCrypt.checkpw(inputPassword, user.getPassword())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Login Successful :  " + user.getUsername()).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found!").show();
        }
    }

    @FXML
    void btnSelectImageOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedImageFile = fileChooser.showOpenDialog(mainContent.getScene().getWindow());

        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgSignupPreview.setFill(new ImagePattern(image));
        }
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) {
        password = txtSignupPassword.getText();
        confirmPassword = txtSignupConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
        }

        if (selectedImageFile != null) {
            imageUrl = CloudinaryUtil.uploadImage(selectedImageFile);
        }

        UserDTO userDTO = new UserDTO(txtSignupName.getText(),txtSignupEmail.getText(),password,imageUrl);

        if(userService.addUser(userDTO)){
            new Alert(Alert.AlertType.INFORMATION, "Registration Successful!").show();
            clearSignUpPage();
            lnkToggleOnAction(null);
        }
    }

    @FXML
    void lnkForgotOnAction(ActionEvent event) {
        //forgot password
    }

    @FXML
    void lnkToggleOnAction(ActionEvent event) {
        if (isLoginView) {
            vboxLogin.setVisible(false);
            vboxLogin.setManaged(false);
            vboxSignup.setVisible(true);
            vboxSignup.setManaged(true);
            lblTitle.setText("Create a new account");
            lblFooterText.setText("Already have an account?");
            lnkToggle.setText("Login");
        } else {
            vboxSignup.setVisible(false);
            vboxSignup.setManaged(false);
            vboxLogin.setVisible(true);
            vboxLogin.setManaged(true);
            lblTitle.setText("Sign in to your account");
            lblFooterText.setText("Don't have an account?");
            lnkToggle.setText("Sign Up");
        }
        isLoginView = !isLoginView;
    }

    private void clearSignUpPage(){
        txtSignupName.setText("");
        txtSignupEmail.setText("");
        txtSignupConfirmPassword.setText("");
        txtSignupPassword.setText("");
    }
}
