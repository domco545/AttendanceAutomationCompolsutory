/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.dal.ConnectionPool;
import attendanceautomationcompolsutory.dal.TeacherDB;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author narma
 */
public class TeacherManager {

    TeacherDB teacher = new TeacherDB();
    ConnectionPool conpool = ConnectionPool.getInstance();

    public Lesson getLesson(int id, String day) {
        Connection con = conpool.checkOut();
        Lesson lesson = teacher.getLesson(con, id, day);
        conpool.checkIn(con);
        return  lesson;
    }
    
    public ArrayList getStudents(int lessonId, String date){
        Connection con = conpool.checkOut();
        ArrayList<Student> students = teacher.getStudents(con, lessonId, date);
        conpool.checkIn(con);
        return students;
    }
}
