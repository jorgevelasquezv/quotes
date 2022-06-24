package co.com.jorge.quotes.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionDS {

    public static Connection getConnection() throws SQLException, NamingException{

        Context initContext = null;

        initContext = new InitialContext();

        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/QuotesDB");

        return ds.getConnection();

    }
}
