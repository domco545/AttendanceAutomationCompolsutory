/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.gui.controller;

import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.bll.TeacherManager;
import attendanceautomationcompolsutory.dal.ITeacherDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author XMdag
 */
public class TeacherMainController implements Initializable {

    //  @FXML
    //  private JFXTreeTableView<Student> tableview;
    @FXML
    private TreeTableColumn<Student, Integer> id;

    @FXML
    private TreeTableColumn<Student, String> fname;
    @FXML
    private TreeTableColumn<Student, String> lname;
    ITeacherDB Iteacher = new TeacherManager();

    private ObservableList<Student> obsStudents = FXCollections.observableArrayList(Iteacher.getStudentData());
    @FXML
    private JFXButton BtnDetail;
    @FXML
    private Label LblCurrentDay;
    @FXML
    private SplitMenuButton selectClassDropdown;
    @FXML
    private Label LblEmail;
    @FXML
    private Label LblName;
    @FXML
    private JFXButton BtnClassOverview;
    @FXML
    private JFXButton BtnLogout;

    Student student;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //
//         id  = new JFXTreeTableColumn<>("id");
//        fname = new JFXTreeTableColumn<>("fname");
//        lname = new JFXTreeTableColumn<>("lname");
//
//        fname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
//                return param.getValue().getValue().nameProperty();
//
//            }
//        });
//        lname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
//                return param.getValue().getValue().lnameProperty();
//
//            }
//        });
//
//        TreeItem<Student> root = new RecursiveTreeItem<Student>(obsStudents, RecursiveTreeObject::getChildren);
//        tableview.getColumns().setAll(fname, lname);
//        tableview.setRoot(root);
//        tableview.setShowRoot(false);

        LblEmail.setText(Iteacher.getTeacherMail(student.getId()));
        LblName.setText(Iteacher.getTeacherName(student.getId()));
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

    @FXML
    private void loadClassOverview(ActionEvent event) {
        // TO DO 
        // Load the selected Class's overview page
    }

}
