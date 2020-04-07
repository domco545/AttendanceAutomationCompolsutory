/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import java.io.InputStream;

/**
 *
 * @author domin
 */
public interface IBllFacade {
    public boolean authenticate(String mail, String pass);

    public boolean emailExist(String mail);

    public void setPass(String text, String newPass);
    
    public void changeProfilePicture(int id, InputStream img);
    
}
