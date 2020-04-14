/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.LoggedUser;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author XMdag
 */
public class TeacherMainController implements Initializable {

    @FXML
    private JFXButton BtnDetail;
    @FXML
    private Label LblCurrentDay;
    @FXML
    private Label LblEmail;
    @FXML
    private Label LblName;
    @FXML
    private JFXButton BtnClassOverview;
    @FXML
    private JFXButton BtnLogout;
    @FXML
    private Label lblClass;
    @FXML
    private ImageView imgProfile;
    
    LoggedUser user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = LoggedUser.getInstance();
        LblName.setText(user.fNmae + " " + user.lName);
        LblEmail.setText(user.email);
        imgProfile.setImage(user.image);
    }

    @FXML
    private void actionLogout(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;

            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/Login.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadStudentDetail(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            // TO DO 
            // Load the selected student's overview page
            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/TeacherStudentDetails.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadClassOverview(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            // TO DO 
            // Load the selected student's overview page
            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/TeacherClassOverview.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TeacherMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
