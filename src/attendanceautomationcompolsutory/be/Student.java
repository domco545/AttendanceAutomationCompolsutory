/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

import javafx.beans.property.IntegerProperty;
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

    public Student(int id, String fname, String lname) {
        id = this.getId();
        fname = this.getName();
        lname = this.getLname();
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

}
