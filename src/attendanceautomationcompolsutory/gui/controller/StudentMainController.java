/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.LoggedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author domin
 */
public class StudentMainController implements Initializable {
    @FXML
    private Label lblName;
    @FXML
    private Text lblDate;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private Label lblEmail;
    @FXML
    private JFXButton editProfileId;
    @FXML
    private JFXButton studentOverviewBtn;
    @FXML
    private JFXToggleButton btnSubjectOne;
    @FXML
    private JFXToggleButton btnSubjectThree;
    @FXML
    private JFXToggleButton btnSubjectTwo;
    @FXML
    private JFXToggleButton btnSubjectFour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoggedUser user = LoggedUser.getInstance();
        lblName.setText(user.fNmae + " " + user.lName);
        lblEmail.setText(user.email);
    }   
    

  

    @FXML
    private void actionLogout(ActionEvent event) {
    }

    @FXML
    private void studentEditProfileButton(ActionEvent event) {
    }

    @FXML
    private void goToOverview(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            // TO DO 
            // Load the selected student's overview page
            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/StudentOverview.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnChecker(ActionEvent event){
    List<Integer> list = new ArrayList<Integer>();
    if(btnSubjectOne.isPressed())
    list.add(1);
    else
    list.add(0);
        if(btnSubjectTwo.isPressed())
    list.add(1);
    else
    list.add(0);
            if(btnSubjectThree.isPressed())
    list.add(1);
    else
    list.add(0);
                if(btnSubjectFour.isPressed())
    list.add(1);
    else
    list.add(0);
                for (Integer integer : list) {
                    System.out.println(integer+"");
            
        }
          
    }
}

