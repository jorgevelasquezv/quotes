package co.com.jorge.quotes.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResources {

    @Produces
    @RequestScoped
    @Named("conn")
    private Connection createConnection() throws SQLException, NamingException {

        Context initContext = new InitialContext();

        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/quotes");

        return ds.getConnection();
    }
}
