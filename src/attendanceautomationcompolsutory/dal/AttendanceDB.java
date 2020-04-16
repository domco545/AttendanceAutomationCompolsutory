/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Attendance;
import attendanceautomationcompolsutory.be.LoggedUser;
import attendanceautomationcompolsutory.be.Subject;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saraf
 */
public class AttendanceDB {

    DBConnection db = new DBConnection();

    public ArrayList<Attendance> getAttendanceByFilter(String subjectName, String periodType, int periodValue) {
        int studentId = LoggedUser.getInstance().id;
        System.out.println(studentId);
        if ("Week".equals(periodType)) {
            //TODO
        } else if ("Month".equals(periodType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2020, periodValue-1, 1);
            Date monthStart = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date monthEnd = calendar.getTime();
            try ( Connection con = db.getConnection()) {
                String sql = "SELECT Attendance.date , Subject.name FROM Attendance\n" +
"JOIN Lesson ON Attendance.lesson_id = Lesson.id\n" +
"JOIN Subject ON Subject.id = Lesson.subject_id\n" +
"WHERE Attendance.student_id = ? AND Subject.name = ? AND date >= ? AND date <= ?  ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, studentId);
                ps.setString(2, subjectName);
                ps.setDate(3, new java.sql.Date(monthStart.getTime()));
                ps.setDate(4, new java.sql.Date(monthEnd.getTime()));
                ResultSet rs = ps.executeQuery();
                ArrayList<Attendance> attendances = new ArrayList<>();
                while (rs.next()) {
                    java.sql.Date date = rs.getDate("date");
                    String subjectNames = rs.getString("name");
                    attendances.add(new Attendance(date,subjectNames, "Present"));
                }
                return attendances;
            } catch (SQLServerException ex) {
                Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
