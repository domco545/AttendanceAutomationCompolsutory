/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Subject;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saraf
 */
public class SubjectDB {

    DBConnection db = new DBConnection();

    public List<Subject> getAllSubject() {
        try (Connection con = db.getConnection()) {
            List<Subject> subjects = new ArrayList();
            String sql = "SELECT * FROM Subject";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Subject subject = new Subject(name, id);
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLServerException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
