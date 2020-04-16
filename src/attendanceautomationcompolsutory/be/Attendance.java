/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.be;

/**
 *
 * @author narma
 */
public class Attendance {

  private String subject;
    private String date;
  

    public Attendance(String date, String subject) {
        this.date = date;
        this.subject = subject;
    }
    
        public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
            
}
