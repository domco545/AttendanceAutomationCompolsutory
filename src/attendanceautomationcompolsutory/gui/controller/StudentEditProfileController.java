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
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        try {
            FXMLLoader loader;
            Parent root = null;
            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/StudentMain.fxml"));
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
    private void changePassword(ActionEvent event) {
        Hash hash = new Hash();
        String oldPass = txtOldPass.getText();
        String newPass = txtNewPass.getText();
        String newPassAgain = txtNewPassAgain.getText();

        if (passValidCheck()) {
            if (bll.oldPassValid(user.id, hash.hashPass(oldPass))) {
                bll.changePass(user.id, hash.hashPass(newPass));
                
                txtOldPass.clear();
                txtNewPass.clear();
                txtNewPassAgain.clear();
                
                Alert changed = new Alert(Alert.AlertType.CONFIRMATION);
                changed.setTitle("Password changed sucsessfully");
            } else {
                Alert wrongPass = new Alert(Alert.AlertType.ERROR);
                wrongPass.setTitle("Wrong password");
                wrongPass.setHeaderText("Old password is not wrong");
                wrongPass.showAndWait();
            }
        }
    }

    private boolean passValidCheck() {
        if (txtOldPass.getText().isEmpty() || txtNewPass.getText().isEmpty() || txtNewPassAgain.getText().isEmpty()) {
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Empty field");
            empty.setHeaderText("All fields have to be filled");
            empty.showAndWait();
            return false;
        }

        if (!txtNewPass.getText().equals(txtNewPassAgain.getText())) {
            Alert notEqual = new Alert(Alert.AlertType.ERROR);
            notEqual.setTitle("Passwords are not the same");
            notEqual.setHeaderText("New password and new password retype needs to match ");
            notEqual.showAndWait();
            return false;
        }
        return true;
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
