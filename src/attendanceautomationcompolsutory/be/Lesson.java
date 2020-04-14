/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

import java.sql.Time;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author narma
 */
public class Lesson {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty subject_id = new SimpleIntegerProperty();
    private String day;
    private Time start_time;
    private Time end_time;
    
    public Lesson(int id, int subject_id, String day, Time start_time, Time end_time)
    {
        id = this.getId();
        subject_id = this.getSubject_id();
        day = this.day;
        start_time = this.start_time;
        end_time = this.end_time;
    }
    
    
    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
    

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private int getSubject_id() {
        return subject_id.get();
    }

    private void setSubject_id(int value) {
        subject_id.set(value);
    }

    private IntegerProperty subject_idProperty() {
        return subject_id;
    }
    

    private int getId() {
        return id.get();
    }

    private void setId(int value) {
        id.set(value);
    }

    private IntegerProperty idProperty() {
        return id;
    }
    
}
