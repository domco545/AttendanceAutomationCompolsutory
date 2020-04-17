/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Student;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author domin
 */
public class TeacherStudentDetailsController {

    @FXML
    private JFXButton homeBack;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private JFXButton btnWeek;
    @FXML
    private JFXButton btnDay;
    @FXML
    private JFXButton btnMonth;
    @FXML
    private JFXButton btnSemester;
    @FXML
    private JFXButton btnTotal;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<?> tableViewPresence;
    @FXML
    private TableColumn<?, ?> columnName;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnPresent;
    @FXML
    private JFXButton btnChangePresent;
    @FXML
    private Label lblName;
    
    Student student;

    @FXML
    private void homeBack(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            // TO DO 
            // Load the selected student's overview page
            loader = new FXMLLoader(getClass().getResource("/attendanceautomationcompolsutory/gui/view/TeacherMain.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TeacherStudentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeWeek(ActionEvent event) {
    }

    @FXML
    private void changeDay(ActionEvent event) {
    }

    @FXML
    private void chngeMonth(ActionEvent event) {
    }

    @FXML
    private void changeSemester(ActionEvent event) {
    }

    @FXML
    private void changeTotal(ActionEvent event) {
    }

    @FXML
    private void changePresent(ActionEvent event) {
    }
    
    public void setStudent(Student student){
        this.student = student;
        lblName.setText(student.getFullName());
    }
}
