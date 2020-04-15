/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narma
 */
public class TeacherDB {

    DBConnection db = new DBConnection();

    public List<Student> getStudentData() {
        try (Connection con = db.getConnection()) {
            List<Student> allstudents = new ArrayList();
            String sql = "SELECT Student.id , Student.firstName , Student.lastName FROM Student";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                int id = r.getInt("id");
                String fname = r.getString("firstName");
                String lname = r.getString("lastName");
                Student student = new Student(id, fname, lname);
                allstudents.add(student);
            }
            return allstudents;
        } catch (SQLServerException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
            }else{
                lesson = new Lesson();
            }
            
            return lesson;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
