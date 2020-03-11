/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saraf
 */
public class LoginController implements Initializable {

    //BllFacade bll = new BllManager();
    //User loggedUser;

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
                System.out.println("logged as ");
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
        String hashed = hashPass(pass);
        //loggedUser = bll.authenticate(name, hashed);

//        if (loggedUser.getId() == -1) {
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("Login");
//            alert.setHeaderText("Wrong name or password");
//
//            alert.showAndWait();
//            return false;
//        }
        return true;
    }

    private String hashPass(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void actionForgotpass(ActionEvent event) throws IOException {
    }
    
    private void mainWindow(ActionEvent event){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/attendanceautomation/gui/view/Main.fxml"));
            Parent root = loader.load();
            
//            if(loggedUser.getPermissionGroup() == 1){
//                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/gui/view/StudentMain.fxml"));
//                root=loader.load();
//                StudentMainController smc = loader.getController();
//                smc.setUser(loggedUser);
//            }else if(loggedUser.getPermissionGroup() == 2){
//                loader = new FXMLLoader(getClass().getResource("/attendanceautomation/gui/view/TeacherMain.fxml"));
//                root = loader.load();
//                TeacherMainController tmc = loader.getController();
//                tmc.setUser(loggedUser);
//            }
            
            Scene scene = new Scene(root);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
