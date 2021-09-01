package practice.classload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JdbcBreakParentalDelegationModelTest
 *
 * @title JdbcBreakParentalDelegationModelTest
 * @Description
 * @Author donglongcheng01
 * @Date 2021/9/1
 **/
public class JdbcBreakParentalDelegationModelTest {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/beauty", "root", "xxx");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection.getClass());
        System.out.println(connection.getClass().getClassLoader());
        System.out.println(Connection.class.getClassLoader());
    }

}
