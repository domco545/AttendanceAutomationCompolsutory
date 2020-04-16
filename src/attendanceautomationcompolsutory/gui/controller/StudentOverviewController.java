/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Attendance;
import attendanceautomationcompolsutory.be.Subject;
import attendanceautomationcompolsutory.dal.AttendanceDB;
import attendanceautomationcompolsutory.dal.SubjectDB;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author narma
 */
public class StudentOverviewController implements Initializable {

    private MenuItem weeks = new MenuItem("weeks");
    private MenuItem months = new MenuItem("months");
    private MenuItem semesters = new MenuItem("semesters");
    @FXML
    private PieChart pieChart;
    @FXML
    private Button preBt;
    @FXML
    private Button nextBt;
    @FXML
    private ComboBox<String> comboxSubject;
    @FXML
    private RadioButton radioBtnWeek;
    @FXML
    private RadioButton radioBtnMonth;
    @FXML
    private RadioButton radioBtnSemester;
    private String periodType;
    private int periodValue;
    @FXML
    private TableColumn<Attendance, Date> dateCol;
    @FXML
    private TableColumn<Attendance, String> attendanceColumn;
    @FXML
    private TableView<?> tableView;

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*   List<MenuItem> periods = new ArrayList();
        periods.add(weeks);
        periods.add(months);
        periods.add(semesters);
        ObservableList<MenuItem> obsperiods = FXCollections.observableArrayList();
        obsperiods.setAll(periods);*/
        SubjectDB subjectDB = new SubjectDB();
        List<Subject> subjects = subjectDB.getAllSubject();
        List<String> subjectNames = new ArrayList<>();
        for (Subject subject : subjects) {
            MenuItem menuItem = new MenuItem(subject.getName());
            subjectNames.add(subject.getName());
        }
        comboxSubject.getItems().addAll(subjectNames);
        ToggleGroup toggleGroup = new ToggleGroup();
        radioBtnWeek.setToggleGroup(toggleGroup);
        radioBtnMonth.setToggleGroup(toggleGroup);
        radioBtnSemester.setToggleGroup(toggleGroup);
        radioBtnWeek.setSelected(true);
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();

                if (rb != null) {
                    if (comboxSubject.getValue() != null) {
                        initFilter();
                    } else {
                        System.out.println("alert");
                    }
                }
            }
        });

        //Piechart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Marked Attendance", 34),
                        new PieChart.Data("Absence", 66));
        pieChart.setData(pieChartData);
    }

    @FXML
    private void goToLogin(ActionEvent event) {
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
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader;
            Parent root = null;
            // TO DO 
            // Load the selected student's overview page
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

    private void classDropDown(ActionEvent event) {
        MenuItem.MENU_VALIDATION_EVENT.getClass();

    }

    void setUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void preBTAction(ActionEvent event) {
    }

    @FXML
    private void nextBTAction(ActionEvent event) {
    }

    @FXML
    private void comboxSubjectAction(ActionEvent event) {
        initFilter();
    }

    private void initFilter() {
        String subjectName;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        subjectName = comboxSubject.getValue();
        if (radioBtnWeek.isSelected()) {
            periodType = radioBtnWeek.getText();
            periodValue = cal.get(Calendar.WEEK_OF_YEAR);
        } else if (radioBtnMonth.isSelected()) {
            periodType = radioBtnMonth.getText();
            periodValue = cal.get(Calendar.MONTH) + 1;
        } else if (radioBtnSemester.isSelected()) {
            periodType = radioBtnSemester.getText();
            periodValue = 0;
        }
        System.out.println(subjectName);
        System.out.println(periodType);
        System.out.println(periodValue);
        AttendanceDB attendanceDB = new AttendanceDB();
        ArrayList<Attendance> attendances = attendanceDB.getAttendanceByFilter(subjectName, periodType, periodValue);
        ObservableList data = FXCollections.observableList(attendances);
        tableView.setItems(data);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

}
