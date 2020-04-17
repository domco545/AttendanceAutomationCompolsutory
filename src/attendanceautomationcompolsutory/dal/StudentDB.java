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

    public void attendanceDefault(Connection con, int student_id, int lesson_id, int present) {
        try {
            String sql = "INSERT INTO Attendance VALUES (?,?,Convert(date, getdate()),?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, student_id);
            pstmt.setInt(2, lesson_id);
            pstmt.setInt(3,present);

            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void confirmAttendance(Connection con, int present, int student_id, int lesson_id)
    {
         try {
            String sql = "UPDATE Attendance SET present = ? WHERE Attendance.student_id = ? AND Attendance.lesson_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setInt(1, present);
            pstmt.setInt(2, student_id);
            pstmt.setInt(3, lesson_id);
           

            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
