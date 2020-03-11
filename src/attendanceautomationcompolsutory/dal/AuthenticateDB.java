/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.LoggedUser;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author domin
 */
public class AuthenticateDB {

    DBConnection db = new DBConnection();

    public boolean authenticateUser(String mail, String pass){
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Person WHERE email = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mail);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String email = rs.getString("email");
                int rights = rs.getInt("access_level");
                
                LoggedUser.init(id, fName, lName, email, rights);
                return true;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
