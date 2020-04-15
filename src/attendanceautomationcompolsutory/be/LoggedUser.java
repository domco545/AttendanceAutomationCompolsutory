/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

import java.io.InputStream;
import javafx.scene.image.Image;

/**
 *
 * @author domin
 */
public class LoggedUser {

    private static LoggedUser loggedUser = null;
    public int id;

    public int getId() {
        return id;
    }
    public String fNmae;
    public String lName;
    public String email;
    public int rights;
    public Image image;

    private LoggedUser(int id, String fName, String lName, String email, int rights, InputStream image) {
        this.id = id;
        this.fNmae = fName;
        this.lName = lName;
        this.email = email;
        this.rights = rights;
        this.image = new Image(image, 350.0, 350.0, true, true);
    }

    public synchronized static LoggedUser init(int id, String fName, String lName, String email, int rights, InputStream image) {
        if (loggedUser != null) {
            throw new AssertionError("Already initialized");
        }

        loggedUser = new LoggedUser(id, fName, lName, email, rights, image);
        return loggedUser;
    }

    public static LoggedUser getInstance() {
        if (loggedUser == null) {
            throw new AssertionError("You have to call init first");
        }

        return loggedUser;
    }
    
    public void newImage(InputStream img){
        this.image = new Image(img, 350.0, 350.0, true, true);
    }

    public static void removeInstance() {
        loggedUser = null;
    }
}
