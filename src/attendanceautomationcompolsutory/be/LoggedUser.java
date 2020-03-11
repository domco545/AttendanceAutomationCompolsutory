/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

/**
 *
 * @author domin
 */
public class LoggedUser {

    private static LoggedUser loggedUser = null;
    public int id;
    public String fNmae;
    public String lName;
    public String email;
    public int rights;

    private LoggedUser(int id, String fName, String lName, String email, int rights) {
        this.id = id;
        this.fNmae = fName;
        this.lName = lName;
        this.email = email;
        this.rights = rights;
    }

    public synchronized static LoggedUser init(int id, String fName, String lName, String email, int rights) {
        if (loggedUser != null) {
            throw new AssertionError("Already initialized");
        }

        loggedUser = new LoggedUser(id, fName, lName, email, rights);
        return loggedUser;
    }

    public static LoggedUser getInstance() {
        if (loggedUser == null) {
            throw new AssertionError("You have to call init first");
        }

        return loggedUser;
    }

    public static void removeInstance() {
        loggedUser = null;
    }
}
