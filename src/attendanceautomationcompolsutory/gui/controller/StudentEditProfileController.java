/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.LoggedUser;
import attendanceautomationcompolsutory.bll.BllManager;
import attendanceautomationcompolsutory.bll.IBllFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author saraf
 */
public class StudentEditProfileController implements Initializable {

    @FXML
    private JFXButton backButtonId;
    @FXML
    private JFXTextField txtOldPass;
    @FXML
    private JFXTextField txtNewPass;
    @FXML
    private JFXTextField txtNewPassAgain;
    @FXML
    private ImageView profilePicture;
    
    LoggedUser user;
    IBllFacade bll = new BllManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = LoggedUser.getInstance();
        profilePicture.setImage(user.image);
    }

    @FXML
    private void backButton(ActionEvent event) {

    }

    @FXML
    private void changePassword(ActionEvent event) {
    }

    @FXML
    private void changeProfilePicture(ActionEvent event) {
        FileChooser selectImage = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("images", "*.jpg", "*.png");
        selectImage.getExtensionFilters().add(filter);

        File originalImg = selectImage.showOpenDialog(null);
        if (originalImg.length() > 30000) {
            Alert imgSize = new Alert(Alert.AlertType.ERROR);
            imgSize.setTitle("Image size");
            imgSize.setHeaderText("Image is too big. Max size of image is 30Kb");
            imgSize.showAndWait();
        } else {
            try {
                FileInputStream fis = new FileInputStream(originalImg);
                user.newImage(fis);
                bll.changeProfilePicture(user.id, fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StudentEditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
