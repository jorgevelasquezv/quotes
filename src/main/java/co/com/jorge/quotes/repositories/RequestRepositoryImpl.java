package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.models.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestRepositoryImpl implements Repository<Request> {

    private Connection conn;

    public RequestRepositoryImpl() {
    }

    public RequestRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Request> listAll() throws SQLException {
        List<Request> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM request");){
            while (resultSet.next()){
                list.add(createRequest(resultSet));
            }
        }
        return list;
    }

    @Override
    public Request find(Request request) throws SQLException {
        Long id = request.getIdRequest();
        Request foundRequest = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM request as r WHERE r.id_request=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundRequest = createRequest(resultSet);
                }
            }
        }
        return foundRequest;
    }

    @Override
    public Request save(Request request) throws SQLException {
        String sql = null;
        if (request.getIdRequest() != null && request.getIdRequest() > 0){
            sql = "UPDATE request SET quantity=?, state=?, id_products=?, registry_date=? WHERE id_request=? ";
        }else {
            sql = "INSERT INTO request(quantity, state, id_products, registry_date) VALUES(?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, request.getQuantity());
            preparedStatement.setBoolean(2, request.isState());
            preparedStatement.setLong(3, request.getIdProduct());
            preparedStatement.setDate(4, new Date(request.getRegistryDate().getTime()));
            if (request.getIdRequest() != null && request.getIdRequest() > 0){
                preparedStatement.setLong(5, request.getIdRequest());
            }
            preparedStatement.executeUpdate();
            if (request.getIdRequest() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        request.setIdRequest(resultSet.getLong("id_request"));
                    }
                }
            }
        }
        return request;
    }

    @Override
    public void deleted(Request request) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM categories WHERE id_category=?")){
            preparedStatement.setLong(1, request.getIdRequest());
            preparedStatement.executeUpdate();
        }
    }

    private Request createRequest(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setIdRequest(resultSet.getLong("id_request"));
        request.setQuantity(resultSet.getInt("quantity"));
        request.setState(resultSet.getBoolean("state"));
        request.setIdProduct(resultSet.getLong("id_products"));
        request.setRegistryDate(resultSet.getDate("registry_date"));
        return request;
    }
}
