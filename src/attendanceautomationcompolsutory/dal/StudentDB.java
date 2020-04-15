/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narma
 */
public class StudentDB {

    DBConnection db = new DBConnection();
    ConnectionPool conpool = ConnectionPool.getInstance();
    
    
    public void submitAttendance(Connection con, int student_id, int lesson_id)
    {
        try{
            String sql = "INSERT INTO Attendance VALUES (?,?,GetDate())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,student_id);
            pstmt.setInt(2,lesson_id);
           
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
