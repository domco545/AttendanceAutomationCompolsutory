/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.be.Attendance;
import attendanceautomationcompolsutory.dal.ConnectionPool;
import attendanceautomationcompolsutory.dal.IStudentDB;
import attendanceautomationcompolsutory.dal.StudentDB;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author narma
 */
public class StudentManager implements IStudentDB {

    ConnectionPool conpool = ConnectionPool.getInstance();
    StudentDB stu = new StudentDB();

    @Override
    public void attendanceDefault(int student_id, int lesson_id, int present) {
        Connection con = conpool.checkOut();

        stu.attendanceDefault(con, student_id, lesson_id,present);
        conpool.checkIn(con);
    }

   /* @Override
    public List<Attendance> getAttendanceForAStudent(int student_id, String subject) {
        Connection con = conpool.checkOut();
        List<Attendance> a = stu.getAttendanceForAStudent(con, student_id, subject);
        conpool.checkIn(con);
        return a;
    }
*/

    @Override
    public void confirmAttendance(int present, int student_id, int lesson_id) {
        Connection con = conpool.checkOut();

        stu.confirmAttendance(con,present, student_id,lesson_id);
        conpool.checkIn(con);
    }
}
