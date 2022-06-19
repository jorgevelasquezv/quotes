package co.com.jorge.quotes.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/quotes";

    private static String user = "root";

    private static String password = "admin";


    private static BasicDataSource pool;


    public static BasicDataSource getInstance() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}
