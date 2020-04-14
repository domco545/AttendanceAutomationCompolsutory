/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import java.io.InputStream;
import attendanceautomationcompolsutory.be.Lesson;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author domin
 */
public interface IBllFacade {
    public boolean authenticate(String mail, String pass);

    public boolean emailExist(String mail);

    public void setPass(String text, String newPass);
    
    public void changeProfilePicture(int id, InputStream img);
    
    public List<Lesson> getDailyLessons(int studentID,String date); 
    
    public boolean oldPassValid(int id, String pass);
    
    public void changePass(int id, String newPass);
}
