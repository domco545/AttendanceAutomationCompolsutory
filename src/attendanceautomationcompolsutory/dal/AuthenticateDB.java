/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.LoggedUser;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.InputStream;
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

    public boolean authenticateUser(String mail, String pass) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT p.*, pp.image FROM Person as p  \n"
                    + "JOIN Profile_Pictures as pp ON p.id = pp.user_id\n"
                    + "WHERE p.email = ? AND p.password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mail);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String email = rs.getString("email");
                int rights = rs.getInt("access_level");
                InputStream image = rs.getBinaryStream("image");

                LoggedUser.init(id, fName, lName, email, rights, image);
                return true;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean emailExist(String mail) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT email FROM Person WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mail);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void setPass(String mail, String newPass) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE Person SET password = ? WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newPass);
            pstmt.setString(2, mail);
            pstmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeProfilePicture(int id, InputStream img) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO Profile_Pictures(user_id, image) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id); // user id
            pstmt.setBinaryStream(2, img);
            pstmt.executeQuery();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
