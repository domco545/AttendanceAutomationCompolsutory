/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.LoggedUser;
import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.bll.TeacherManager;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView<Student> tableViewStudents;
    @FXML
    private TableColumn<Student, String> columnStudent;
    @FXML
    private TableColumn<Student, Boolean> columnPresence;

    LoggedUser user;
    Lesson currentLesson;
    TeacherManager tm;
    private ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tm = new TeacherManager();
        user = LoggedUser.getInstance();
        LblName.setText(user.fNmae + " " + user.lName);
        LblEmail.setText(user.email);
        imgProfile.setImage(user.image);

        columnStudent.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnPresence.setCellValueFactory(new PropertyValueFactory<>("lessonPresent"));

        if (haveLesson() == true) {
            getStudents();
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/TeacherStudentDetails.fxml"));
            Parent root = loader.load();
            TeacherStudentDetailsController tsdc = loader.getController();
            tsdc.setStudent(tableViewStudents.getSelectionModel().getSelectedItem());
        } catch (IOException ex) {
            Logger.getLogger(TeacherClassOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean haveLesson() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        LblCurrentDay.setText(formatter.format(date));

        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        currentLesson = tm.getLesson(user.id, dayOfWeek);

        if (currentLesson.getSubject_name().isEmpty() || currentLesson.getSubject_name() == null) {
            return false;
        } else {
            lblClass.setText(currentLesson.toString());
            return true;
        }
    }

    private void getStudents() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String formatedDate = formatter.format(date);

        students = FXCollections.observableArrayList(tm.getStudents(currentLesson.getId(), formatedDate));
        tableViewStudents.setItems(students);
    }
}
