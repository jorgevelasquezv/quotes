package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.models.RequestProduct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequestProductRepositoryImpl implements Repository<RequestProduct> {

    @Inject
    @Named("conn")
    private Connection conn;

    public RequestProductRepositoryImpl() {
    }

//    public RequestProductRepositoryImpl(Connection conn) {
//        this.conn = conn;
//    }

    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<RequestProduct> listAll() throws SQLException {
        List<RequestProduct> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT r.*, p.*, c.name as category FROM request as r " +
                     "INNER JOIN  products as p ON (r.id_products = p.id_products) INNER JOIN categories as c ON " +
                     "(p.id_categories = c.id_category) ");){
            while (resultSet.next()){
                list.add(createRequestProduct(resultSet));
            }
        }
        return list;
    }

    @Override
    public RequestProduct findById(RequestProduct requestProduct) throws SQLException {
        Long id = requestProduct.getIdRequest();
        RequestProduct foundRequestProduct = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement( "SELECT r.*, p.*, c.name as category FROM request as r " +
                "INNER JOIN  products as p ON (r.id_products = p.id_products) INNER JOIN categories as c ON " +
                "(p.id_categories = c.id_category) WHERE r.id_request=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundRequestProduct = createRequestProduct(resultSet);
                }
            }
        }
        return foundRequestProduct;
    }

    @Override
    public RequestProduct findByName(RequestProduct requestProduct) throws SQLException {
        return null;
    }

    @Override
    public RequestProduct save(RequestProduct requestProduct) throws SQLException {
        String sql = null;
        if (requestProduct.getIdRequest() != null && requestProduct.getIdRequest() > 0){
            sql = "UPDATE request SET quantity=?, state=?, id_products=?, initial_date=?, final_date=? WHERE id_request=? ";
        }else {
            sql = "INSERT INTO request(quantity, state, id_products, initial_date, final_date) VALUES(?, ?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, requestProduct.getQuantity());
            preparedStatement.setBoolean(2, requestProduct.isState());
            preparedStatement.setLong(3, requestProduct.getProduct().getIdProduct());
            preparedStatement.setDate(4, new Date(requestProduct.getInitialDate().getTime()));
            preparedStatement.setDate(5, new Date(requestProduct.getFinalDate().getTime()));
            if (requestProduct.getIdRequest() != null && requestProduct.getIdRequest() > 0){
                preparedStatement.setLong(6, requestProduct.getIdRequest());
            }
            preparedStatement.executeUpdate();
            if (requestProduct.getIdRequest() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        requestProduct.setIdRequest(resultSet.getLong(1));
                    }
                }
            }
        }
        return requestProduct;
    }

    @Override
    public void delete(RequestProduct requestProduct) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM categories WHERE id_category=?")){
            preparedStatement.setLong(1, requestProduct.getIdRequest());
            preparedStatement.executeUpdate();
        }
    }

    private RequestProduct createRequestProduct(ResultSet resultSet) throws SQLException {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setIdRequest(resultSet.getLong("id_request"));
        requestProduct.setQuantity(resultSet.getInt("quantity"));
        requestProduct.setState(resultSet.getBoolean("state"));

        Product product = new Product();
        product.setIdProduct(resultSet.getLong("id_products"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getLong("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setRegistryDate(resultSet.getDate("registry_date"));
        requestProduct.setProduct(product);

        Category category = new Category();
        category.setIdCategory(resultSet.getLong("id_categories"));
        category.setName(resultSet.getString("category"));
        requestProduct.setCategory(category);

        requestProduct.setInitialDate(resultSet.getDate("initial_date") );
        requestProduct.setFinalDate(resultSet.getDate("final_date"));
        return requestProduct;
    }
}
