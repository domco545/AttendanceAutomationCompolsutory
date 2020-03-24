package tests;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import attendanceautomationcompolsutory.be.Student;
import attendanceautomationcompolsutory.dal.TeacherDB;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author narma
 */
public class test {
    //private TeacherDB teacher;
    public test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
     @Test
    public void testGetTeacherMail()
    {
       TeacherDB teacher = new TeacherDB();
       String exp = "teacher@easv.dk";
       int id = 42;
      String result = teacher.getTeacherMail(id);
        assertEquals(exp,result);
        
        
    }
}
