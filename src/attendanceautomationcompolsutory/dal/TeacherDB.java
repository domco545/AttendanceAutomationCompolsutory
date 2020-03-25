/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narma
 */
public class TeacherDB implements ITeacherDB {

    DBConnection db = new DBConnection();

    public List<Student> getStudentData() {
        try ( Connection con = db.getConnection()) {
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

    @Override
    public String getTeacherMail(int id) {
        try ( Connection con = db.getConnection()) {
            String sql = "SELECT Teacher.email FROM Teacher";
            Statement s = con.createStatement();
            String mail = ""+s.getResultSet();
            return mail;
            
            
        } catch (SQLServerException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getTeacherName(int id) {
         try ( Connection con = db.getConnection()) {
            String sql = "SELECT Teacher.lastName FROM Teacher";
            Statement s = con.createStatement();
            String mail = ""+s.getResultSet();
            return mail;
            
            
        } catch (SQLServerException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
