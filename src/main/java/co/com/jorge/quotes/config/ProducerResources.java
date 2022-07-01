package co.com.jorge.quotes.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/quotes")
    private DataSource ds;
    @Produces
    @RequestScoped
    @ConnectionMySQL
    private Connection createConnection() throws SQLException, NamingException {
        return ds.getConnection();
    }

    public void closeConnection(@Disposes @ConnectionMySQL Connection connection) throws SQLException {
            connection.close();
    }
}
