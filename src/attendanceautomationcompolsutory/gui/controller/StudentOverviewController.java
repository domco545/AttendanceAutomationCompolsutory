/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Subject;
import attendanceautomationcompolsutory.dal.SubjectDB;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TreeTableColumn;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class StudentOverviewController implements Initializable {

    @FXML
    private SplitMenuButton classesdropdown;
    private SplitMenuButton periodsdropdown;

    private MenuItem weeks = new MenuItem("weeks");
    private MenuItem months = new MenuItem("months");
    private MenuItem semesters = new MenuItem("semesters");
    private List<MenuItem> subjectsMenuItem = new ArrayList();

    @FXML
    private PieChart pieChart;
    @FXML
    private TreeTableColumn<?, ?> collumDate;
    @FXML
    private TreeTableColumn<?, ?> collumStudentStautes;

     public StudentOverviewController() {
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<MenuItem> periods = new ArrayList();
        periods.add(weeks);
        periods.add(months);
        periods.add(semesters);
        ObservableList<MenuItem> obsperiods = FXCollections.observableArrayList();
        obsperiods.setAll(periods);
       // periodsdropdown.getItems().setAll(obsperiods);
        SubjectDB subjectDB = new SubjectDB();
        List<Subject> subjects = subjectDB.getAllSubject();
        for (Subject subject : subjects) {
            MenuItem menuItem = new MenuItem(subject.getName());
            subjectsMenuItem.add(menuItem);
        }
        ObservableList<MenuItem> obssubjects = FXCollections.observableArrayList();
        obssubjects.setAll(subjectsMenuItem);
        classesdropdown.getItems().setAll(obssubjects);

        //Piechart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Marked Attendance", 34),
                        new PieChart.Data("Absence", 66));
        pieChart.setData(pieChartData);
    }

    @FXML
    private void goToLogin(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }

    @FXML
    private void classDropDown(ActionEvent event) {
        MenuItem.MENU_VALIDATION_EVENT.getClass();

    }

    void setUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
