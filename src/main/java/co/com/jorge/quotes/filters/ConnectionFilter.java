package co.com.jorge.quotes.filters;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

//    @Inject
//    @ConnectionMySQL
//    private Connection conn;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        try{
//            Connection connRequest = this.conn;
//            if (connRequest.getAutoCommit()) {
//                connRequest.setAutoCommit(false);
//            }
            try {
                chain.doFilter(request, response);
//                connRequest.commit();
            } catch (ServiceJdbcException e) {
//                connRequest.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }
}
