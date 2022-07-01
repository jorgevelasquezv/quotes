package co.com.jorge.quotes.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResources {

    @Resource(name = "jdbc/quotes")
    private DataSource ds;
    @Produces
    @RequestScoped
    @ConnectionMySQL
    private Connection createConnection() throws SQLException, NamingException {
        return ds.getConnection();
    }
}
