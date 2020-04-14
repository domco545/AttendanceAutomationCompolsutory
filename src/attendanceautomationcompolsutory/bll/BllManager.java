/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.be.Lesson;
import attendanceautomationcompolsutory.dal.AuthenticateDB;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author domin
 */
public class BllManager implements IBllFacade {
    AuthenticateDB auth = new AuthenticateDB();
            
    @Override
    public boolean authenticate(String mail, String pass) {
        return auth.authenticateUser(mail, pass);
    }

    @Override
    public boolean emailExist(String mail) {
        return auth.emailExist(mail);
    }

    @Override
    public void setPass(String mail, String newPass) {
        auth.setPass(mail, newPass);
    }

    @Override
    public void changeProfilePicture(int id, InputStream img) {
        auth.changeProfilePicture(id, img);
    }
    public List<Lesson> getDailyLessons(int studentID,String date) {
       return auth.getDailyLessons(studentID, date);
    }

    @Override
    public boolean oldPassValid(int id, String pass) {
        return auth.oldPassValid(id, pass);
    }

    @Override
    public void changePass(int id, String newPass) {
        auth.changePass(id, newPass);
    }

}
