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

    public boolean authenticateUser(String mail, String pass) {
        try ( Connection con = db.getConnection()) {
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
        try ( Connection con = db.getConnection()) {
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
        try ( Connection con = db.getConnection()) {
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

    public List<Lesson> getDailyLessons(int studentID,Date date) {
        try ( Connection con = db.getConnection()) {
            List<Lesson> dailylessons = new ArrayList();
            String sql = "SELECT Subject.name, Lesson.start_time, Lesson.end_time FROM Lesson\n"
                    + "JOIN Subject ON Subject.id = subject_id\n"
                    + "JOIN Student_have_lesson ON Student_have_lesson.lesson_id = Lesson.id\n"
                    + "WHERE Student_have_lesson.student_id = ? AND Lesson.date =? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setDate(2, date);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                int subid = rs.getInt("subject_id");
                Date dat = rs.getDate("date");
                Time start = rs.getTime("start_time");
                Time end = rs.getTime("end_time");
                Lesson lesson = new Lesson(id,subid,dat,start,end);
                dailylessons.add(lesson);
                return dailylessons;
            }
            
        } catch (Exception e) {
        }
        return null;
    }
}
