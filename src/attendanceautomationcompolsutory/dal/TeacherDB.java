/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narma
 */
public class TeacherDB {

    public Lesson getLesson(Connection con, int id, String day) {
        try {
            Lesson lesson;
            String sql = "SELECT Subject.name, Lesson.id, Lesson.start_time, Lesson.end_time FROM Lesson"
                    + "JOIN Subject ON Subject.id = subject_id"
                    + "WHERE (Subject.teacher_id = ?) AND (Lesson.date = ?) AND "
                    + "(CONVERT(TIME,GETDATE()) BETWEEN Lesson.start_time AND Lesson.end_time)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer lessonId = rs.getInt("id");
                String name = rs.getString("name");
                Time start = rs.getTime("start_time");
                Time end = rs.getTime("end_time");
                lesson = new Lesson(lessonId, name, start, end);
            } else {
                lesson = new Lesson();
            }

            return lesson;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList getStudents(Connection con, int lessonId, String date) {
        try {
            ArrayList<Student> students = new ArrayList();
            String sql = "SELECT Attendance.*, Student.id, Student.fName, Student.lName FROM Attendance"
                    + "JOIN Person ON Attendance.student_id = Person.id"
                    + "WHERE Attendance.lesson_id = ?, Attendance.date = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, lessonId);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                boolean currentLesson = rs.getBoolean("present");
                Student student = new Student(id, fName, lName, currentLesson);
                students.add(student);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
