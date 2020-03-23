/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author domin
 */
public class test {
    

    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(""); //insert image path
        FileInputStream fis = new FileInputStream(file);
        DBConnection db = new DBConnection();
        
        try(Connection con = db.getConnection()){
            String sql = "INSERT INTO Profile_Pictures(user_id, image) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 22); // user id
            pstmt.setBinaryStream(2, fis);
            pstmt.executeQuery();
        
        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
