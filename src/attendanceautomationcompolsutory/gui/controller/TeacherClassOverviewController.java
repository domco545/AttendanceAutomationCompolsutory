/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.be.StudentClass;
import attendanceautomationcompolsutory.bll.TeacherManager;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author domin
 */
public class TeacherClassOverviewController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private ChoiceBox<StudentClass> choiceBoxClass;
    @FXML
    private TableView<Student> tableViewStudents;
    @FXML
    private TableColumn<Student, String> columnName;

    TeacherManager tm;
    private ObservableList<StudentClass> classes;
    private ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tm = new TeacherManager();
        getClasses();

        columnName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        choiceBoxClass.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentClass>() {
            @Override
            public void changed(ObservableValue<? extends StudentClass> observableValue, StudentClass student, StudentClass student2) {
                getStudentsTable();
            }
        });
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
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
            Logger.getLogger(TeacherClassOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getClasses() {
        classes = FXCollections.observableArrayList(tm.getClasses());
        choiceBoxClass.getItems().addAll(classes);
    }

    private void getStudentsTable() {
        students = FXCollections.observableArrayList(tm.getStudentTable(choiceBoxClass.getSelectionModel().getSelectedItem().getId()));
        tableViewStudents.setItems(students);
    }
}
