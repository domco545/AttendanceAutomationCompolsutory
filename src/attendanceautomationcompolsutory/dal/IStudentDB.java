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
     public void submitAttendance(int student_id, int lesson_id);
     //public List<Attendance> getAttendanceForAStudent(int student_id, String subject);
}
