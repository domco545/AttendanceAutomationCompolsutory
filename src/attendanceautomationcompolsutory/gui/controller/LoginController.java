/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.Hash;
import attendanceautomationcompolsutory.be.LoggedUser;
import attendanceautomationcompolsutory.bll.BllManager;
import attendanceautomationcompolsutory.bll.IBllFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    IBllFacade bll = new BllManager();
    LoggedUser user;

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXRadioButton radioRemember;
    @FXML
    private JFXButton btnLogin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionLogin(ActionEvent event) {
        String name = txtName.getText();
        String pass = txtPass.getText();

        if (validate(name, pass)) {
            if (authenticate(name, pass)) {
                mainWindow(event);
            }
        }
    }

    private boolean validate(String name, String pass) {
        if (name.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Login");
            alert.setHeaderText("Password and Name needs to be filled");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean authenticate(String name, String pass) {
        Hash hash = new Hash();
        String hashed = hash.hashPass(pass);
        boolean auth = bll.authenticate(name, hashed);

        if (auth == true) {
            user = LoggedUser.getInstance();
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Login");
            alert.setHeaderText("Wrong name or password");

            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void actionForgotpass(ActionEvent event) throws IOException {
    }

    private void mainWindow(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            
            if (user.rights == 1) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/StudentMain.fxml"));
                root = loader.load();
            } else if (user.rights == 2) {
                loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/TeacherMain.fxml"));
                root = loader.load();
            }
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
