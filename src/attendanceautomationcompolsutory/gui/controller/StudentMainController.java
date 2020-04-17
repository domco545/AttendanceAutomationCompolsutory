/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.LoggedUser;
import attendanceautomationcompolsutory.be.Subject;
import attendanceautomationcompolsutory.dal.SubjectDB;
import attendanceautomationcompolsutory.bll.BllManager;
import attendanceautomationcompolsutory.bll.IBllFacade;
import attendanceautomationcompolsutory.bll.StudentManager;
import attendanceautomationcompolsutory.dal.IStudentDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML
    private ImageView imgViewProfile;

    IBllFacade bll = new BllManager();
    IStudentDB studentbll = new StudentManager();
    LoggedUser user = LoggedUser.getInstance();
    @FXML
    private Label errorlabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //LoggedUser user = LoggedUser.getInstance();
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        lblName.setText(user.fNmae + " " + user.lName);
        lblEmail.setText(user.email);
        imgViewProfile.setImage(user.image);

        checkVPN();
        getCurrentDate();
        initSubjectLabel();

        try {
            getDailySchedule();
        } catch (ParseException ex) {
            Logger.getLogger(StudentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/attendanceautomationcompolsutory/gui/view/Login.fxml"));

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void studentEditProfileButton(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;

            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/studentEditProfile.fxml"));
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

    private void btnChecker(ActionEvent event) {
        List<Integer> list = new ArrayList<Integer>();
        if (btnSubjectOne.isPressed()) {
            list.add(1);
        } else {
            list.add(0);
        }
        if (btnSubjectTwo.isPressed()) {
            list.add(1);
        } else {
            list.add(0);
        }
        if (btnSubjectThree.isPressed()) {
            list.add(1);
        } else {
            list.add(0);
        }
        if (btnSubjectFour.isPressed()) {
            list.add(1);
        } else {
            list.add(0);
        }
        for (Integer integer : list) {
            System.out.println(integer + "");

        }

    }

    @FXML
    private void confirmAttendance(ActionEvent event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        List<Lesson> lessons = bll.getDailyLessons(user.id, dayOfWeek);
        if (lessons.size()==1 &&!btnSubjectOne.getText().isEmpty() && btnSubjectOne.isSelected()) {

            studentbll.submitAttendance(user.id,lessons.get(0).getId());
            btnSubjectOne.setDisable(true);
            errorlabel.setText("You successfully submitted your attendance");
        }
        else if (lessons.size()==2 && !btnSubjectOne.getText().isEmpty() && btnSubjectOne.isSelected() && !btnSubjectTwo.getText().isEmpty() && btnSubjectTwo.isSelected())
        {
            studentbll.submitAttendance(user.id,lessons.get(0).getId());
            studentbll.submitAttendance(user.id,lessons.get(1).getId());
            btnSubjectOne.setDisable(true);
            btnSubjectTwo.setDisable(true);
            errorlabel.setText("You successfully submitted your attendance");
        }
        else if(!btnSubjectOne.getText().isEmpty())
        {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("There is no selected subject");

            alert.showAndWait();
        }
        else if(!btnSubjectOne.getText().isEmpty() && !btnSubjectTwo.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("There are no selected subjects");

            alert.showAndWait();
        }
    }

    private void checkVPN() {
        // not 100% sure this works all the time 
        //doesnt work for school vpn client 
        //it creates ethernet interface instead of vpn interfaces
        boolean found = false;
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp()) {
                    String iName = networkInterface.getName();
                    System.out.println(iName);
                    if (iName.contains("tun") || iName.contains("ppp") || iName.contains("pptp")) {
                        System.out.println("VPN connection found");
                        found = true;
                    }
                }
            }
            if (found == false) {
                System.out.println("VPN connection not found");
            }
        } catch (Exception ex) {
            System.out.println("No interfaces found");
        }
    }

    private void getCurrentDate() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar time = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            lblDate.setText(simpleDateFormat.format(time.getTime()));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initSubjectLabel() {
        SubjectDB subjectDB = new SubjectDB();
        List<Subject> subjects = subjectDB.getAllSubject();

    }

    private void getDailySchedule() throws ParseException {
        //LoggedUser user = LoggedUser.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        List<Lesson> lessons = bll.getDailyLessons(user.id, dayOfWeek);

        //ObservableList<Lesson> daily = FXCollections.observableArrayList();
        /* for (Lesson lesson : lessons) {
            daily.add(lesson);
        }*/
        if (lessons.isEmpty() == true) {
            btnSubjectOne.setDisable(true);
            btnSubjectTwo.setDisable(true);
            btnSubjectThree.setDisable(true);
            btnSubjectFour.setDisable(true);
        } else if (lessons.size() == 1) {
            btnSubjectOne.setText(String.valueOf(lessons.get(0)));
            btnSubjectTwo.setDisable(true);
            btnSubjectThree.setDisable(true);
            btnSubjectFour.setDisable(true);
        } else if (lessons.size() == 2) {
            btnSubjectOne.setText(String.valueOf(lessons.get(0)));
            btnSubjectTwo.setText(String.valueOf(lessons.get(1)));
            btnSubjectThree.setDisable(true);
            btnSubjectFour.setDisable(true);
        } else if (lessons.size() == 3) {
            btnSubjectOne.setText(String.valueOf(lessons.get(0)));
            btnSubjectTwo.setText(String.valueOf(lessons.get(1)));
            btnSubjectThree.setText(String.valueOf(lessons.get(2)));
            btnSubjectFour.setDisable(true);
        }

    }
}
