package co.com.jorge.quotes.interceptors;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.sql.Connection;

@TransactionalJDBC
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @ConnectionMySQL
    private Connection connection;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        if (connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        try {
            Object result = invocationContext.proceed();
            connection.commit();
            return result;
        }catch (ServiceJdbcException e){
            connection.rollback();
            throw e;
        }
    }
}
