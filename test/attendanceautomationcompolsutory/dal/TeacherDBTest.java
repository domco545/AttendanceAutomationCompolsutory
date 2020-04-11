/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import attendanceautomationcompolsutory.be.Student;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author narma
 */
public class TeacherDBTest {
    
    public TeacherDBTest() {
    }

    /**
     * Test of getStudentData method, of class TeacherDB.
     */
    @Test
    public void testGetStudentData() {
        System.out.println("getStudentData");
        TeacherDB instance = new TeacherDB();
        List<Student> expResult = null;
        List<Student> result = instance.getStudentData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeacherMail method, of class TeacherDB.
     */
    @Test
    public void testGetTeacherMail() {
        System.out.println("getTeacherMail");
        int id = 0;
        TeacherDB instance = new TeacherDB();
        String expResult = "";
        String result = instance.getTeacherMail(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeacherName method, of class TeacherDB.
     */
    @Test
    public void testGetTeacherName() {
        System.out.println("getTeacherName");
        int id = 1;
        TeacherDB instance = new TeacherDB();
        String expResult = "Stegger";
        String result = instance.getTeacherName(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
