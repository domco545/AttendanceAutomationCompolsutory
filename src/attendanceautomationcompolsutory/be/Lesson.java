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

    private int id;
    private String subject_name;
    private Time start_time;
    private Time end_time;

    public Lesson(int id, String subject_name, Time start_time, Time end_time) {
        this.id = id;
        this.subject_name = subject_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    //dont remove this 
    public Lesson() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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

    @Override
    public String toString() {
        return subject_name + " " + start_time + " " + end_time;
    }

}
