package co.com.jorge.quotes.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<R> {

    void setConnection(Connection conn);

    List<R> listAll() throws SQLException;

    R find(R r) throws SQLException;

    R findByName(R r) throws SQLException;

    R save(R r) throws SQLException;

    void deleted(R r) throws SQLException;

}
