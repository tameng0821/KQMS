package test;
/**
 * @author Yanjiang
 * @create 2019-07-23 16:53
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.SQLException;

/**
 *@ClassName DBConnection
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/23 16:53
 *@Version 1.0
 **/
public class DBConnection {
    @Test
    public void getConnect() throws SQLException {
//        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
//        String query = (String) runner.query("select * from attendancetype", new ScalarHandler(2));
//        System.out.println(query);
//        String a="aaa";
//        String b="aaa";
//        System.out.println(a.equals(b));
    }
}
