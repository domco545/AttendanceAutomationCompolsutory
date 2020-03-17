/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.bll;

import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.dal.ITeacherDB;
import attendanceautomationcompolsutory.dal.TeacherDB;
import java.util.List;

/**
 *
 * @author narma
 */
public class TeacherManager implements ITeacherDB {

    TeacherDB teacher = new TeacherDB();

    @Override
    public List<Student> getStudentData() {
        List<Student> allstudents = teacher.getStudentData();
        return allstudents;
    }

    @Override
    public String getTeacherMail(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTeacherName(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
