/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.dal.AuthenticateDB;

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

}
