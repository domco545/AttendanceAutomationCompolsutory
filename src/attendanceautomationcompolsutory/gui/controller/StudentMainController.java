/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.LoggedUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author domin
 */
public class StudentMainController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private Label lblName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoggedUser user = LoggedUser.getInstance();
        lblId.setText(user.id+"");
        lblName.setText(user.fNmae+" "+user.lName);
    }   
}
