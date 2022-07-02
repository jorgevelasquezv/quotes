package co.com.jorge.quotes.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<R> {

    void setConnection(Connection conn);

    List<R> listAll() throws SQLException;

    R findById(R r) throws SQLException;

    R findByName(R r) throws SQLException;

    R save(R r) throws SQLException;

    void delete(R r) throws SQLException;

}
