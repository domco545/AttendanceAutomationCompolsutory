/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomationcompolsutory.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author narma
 */
public class TeacherDB implements ITeacherDB {

    SQLServerDataSource ds = new SQLServerDataSource();

    public TeacherDB() {
        ds.setDatabaseName("AttendanceGentleman");
        ds.setUser("CSe19B_3");
        ds.setPassword("CSe19B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
}
