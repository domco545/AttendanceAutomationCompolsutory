/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Attendance;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author domin
 */
public interface IStudentDB {
     public void attendanceDefault(int student_id, int lesson_id, int present);
    public void confirmAttendance(int present, int student_id, int lesson_id);
}
