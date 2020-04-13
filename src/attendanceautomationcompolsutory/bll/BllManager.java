/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.dal.AuthenticateDB;
import attendanceautomationcompolsutory.dal.ConnectionPool;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.sql.Connection;


/**
 *
 * @author domin
 */
public class BllManager implements IBllFacade {
    AuthenticateDB auth = new AuthenticateDB();
    ConnectionPool conpool = ConnectionPool.getInstance();
            
    @Override
    public boolean authenticate(String mail, String pass) {
        Connection con = conpool.checkOut();
        boolean res  = auth.authenticateUser(con, mail, pass);
        conpool.checkIn(con);
        return res;
    }

    @Override
    public boolean emailExist(String mail) {
        Connection con = conpool.checkOut();
        boolean res = auth.emailExist(con, mail);
        conpool.checkIn(con);
        return res;
    }

    @Override
    public void setPass(String mail, String newPass) {
        Connection con = conpool.checkOut();
        auth.setPass(con, mail, newPass);
        conpool.checkIn(con);
    }

    @Override
    public void changeProfilePicture(int id, InputStream img) {
        Connection con = conpool.checkOut();
        auth.changeProfilePicture(con, id, img);
        conpool.checkIn(con);
    }
    public List<Lesson> getDailyLessons(int studentID,Date date) {
        Connection con = conpool.checkOut();
        List<Lesson> res = auth.getDailyLessons(con, studentID, date);
        conpool.checkIn(con);
       return res;
    }

    @Override
    public boolean oldPassValid(int id, String pass) {
        Connection con = conpool.checkOut();
        boolean res = auth.oldPassValid(con, id, pass);
        conpool.checkIn(con);
        return res;
    }

    @Override
    public void changePass(int id, String newPass) {
        Connection con = conpool.checkOut();
        auth.changePass(con, id, newPass);
        conpool.checkIn(con);
    }
}
