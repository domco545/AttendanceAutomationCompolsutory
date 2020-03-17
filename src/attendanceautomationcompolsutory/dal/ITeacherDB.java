/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Student;
import java.util.List;

/**
 *
 * @author domin
 */
public interface ITeacherDB {
    public List<Student> getStudentData();
    public String getTeacherMail(int id);
    public String getTeacherName(int id);
    
}
