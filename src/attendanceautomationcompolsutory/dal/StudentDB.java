/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Attendance;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narma
 */
public class StudentDB {

    DBConnection db = new DBConnection();
    ConnectionPool conpool = ConnectionPool.getInstance();

    public void submitAttendance(Connection con, int student_id, int lesson_id) {
        try {
            String sql = "INSERT INTO Attendance VALUES (?,?,GetDate())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, student_id);
            pstmt.setInt(2, lesson_id);

            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Attendance> getAttendanceForAStudent(Connection con, int student_id, String subject) {
        try {
            List<Attendance> dates = new ArrayList();
            String sql = "SELECT Attendance.date , Subject.name FROM Attendance\n"
                    + "JOIN Lesson ON Attendance.lesson_id = Lesson.id\n"
                    + "JOIN Subject ON Subject.id = Lesson.subject_id\n"
                    + "WHERE Attendance.student_id = ? AND Subject.name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, student_id);
            pstmt.setString(2, subject);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                String name = rs.getString("subject");
                String date = rs.getString("date");
                Attendance at = new Attendance(date,name);
                dates.add(at);
            }
            return dates;

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
