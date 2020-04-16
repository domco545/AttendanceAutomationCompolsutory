/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.be.LoggedUser;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author domin
 */
public class AuthenticateDB {

    DBConnection db = new DBConnection();
    ConnectionPool conpool = ConnectionPool.getInstance();

    public boolean authenticateUser(Connection con, String mail, String pass) {
        try {
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
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean emailExist(Connection con, String mail) {
        try{
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

    public void setPass(Connection con, String mail, String newPass) {
        try{
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

    public void changeProfilePicture(Connection con, int id, InputStream img) {
        try{
            String sql = "UPDATE Profile_Pictures SET image = ? WHERE user_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setBinaryStream(1, img);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Lesson> getDailyLessons(Connection con, int studentID, String date) {
        try{
            List<Lesson> dailylessons = new ArrayList();
            String sql = "SELECT Lesson.id,Subject.name, Lesson.start_time, Lesson.end_time FROM Lesson\n"
                    + "JOIN Subject ON Subject.id = subject_id\n"
                    + "JOIN Student_have_lesson ON Student_have_lesson.lesson_id = Lesson.id\n"
                    + "WHERE Student_have_lesson.student_id = ? AND Lesson.day =? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Time start = rs.getTime("start_time");
                Time end = rs.getTime("end_time");
                Lesson lesson = new Lesson(id,name, start, end);
                dailylessons.add(lesson);
                
            }
                return dailylessons;
         } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean oldPassValid(Connection con, int id, String pass) {
        try{
            String sql = "SELECT password FROM Person WHERE id = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void changePass(Connection con, int id, String newPass) {
        try{
            String sql = "UPDATE Person SET password = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newPass);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
