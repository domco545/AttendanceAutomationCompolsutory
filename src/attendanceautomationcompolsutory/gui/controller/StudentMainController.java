/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.LoggedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author domin
 */
public class StudentMainController implements Initializable {

    private Label lblId;
    @FXML
    private Text lblName;
    @FXML
    private Text lblDate;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private JFXToggleButton btnSubjectOne;
    @FXML
    private JFXToggleButton btnSubjectThree;
    @FXML
    private JFXToggleButton btnSubjectTwo;
    @FXML
    private JFXToggleButton btnSubjectFour;
    @FXML
    private ImageView imgProfile;
    @FXML
    private Text lblEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoggedUser user = LoggedUser.getInstance();
        lblId.setText(user.id+"");
        lblName.setText(user.fNmae+" "+user.lName);
    }   

    @FXML
    private void actionEditProfile(ActionEvent event) {
    }

    @FXML
    private void actionLogout(ActionEvent event) {
    }
}
