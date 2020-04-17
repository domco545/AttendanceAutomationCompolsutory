/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author narma
 */
public class Student {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty fname = new SimpleStringProperty();
    private final StringProperty lname = new SimpleStringProperty();
    private final BooleanProperty lessonPresent = new SimpleBooleanProperty();
    private final StringProperty fullName = new SimpleStringProperty();

    public Student(int id, String fname, String lname, boolean lessonPresent) {
        this.id.set(id);
        this.fname.set(fname);
        this.lname.set(lname);
        this.lessonPresent.set(lessonPresent);
        this.fullName.set(fname + " " + lname);
    }

    public Student(int id, String fname, String lname) {
        this.id.set(id);
        this.fname.set(fname);
        this.lname.set(lname);
        this.fullName.set(fname + " " + lname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String value) {
        lname.set(value);
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public String getName() {
        return fname.get();
    }

    public void setName(String value) {
        fname.set(value);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String value) {
        fullName.set(value);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty nameProperty() {
        return fname;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    private boolean isLessonPresent() {
        return lessonPresent.get();
    }

    private void setLessonPresent(boolean value) {
        lessonPresent.set(value);
    }

    private BooleanProperty lessonPresentProperty() {
        return lessonPresent;
    }
}
