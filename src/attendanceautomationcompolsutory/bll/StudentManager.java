/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.dal.ConnectionPool;
import attendanceautomationcompolsutory.dal.IStudentDB;
import attendanceautomationcompolsutory.dal.StudentDB;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author narma
 */
public class StudentManager implements IStudentDB {
    ConnectionPool conpool = ConnectionPool.getInstance();
    StudentDB stu = new StudentDB();

    @Override
    public void submitAttendance(int student_id, int lesson_id) {
        Connection con = conpool.checkOut();
        
        stu.submitAttendance(con, student_id, lesson_id);
        conpool.checkIn(con);
    }
    
}
