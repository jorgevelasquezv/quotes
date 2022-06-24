package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryImpl implements Repository<Product> {

    private Connection conn;

    private CategoryRepositoryImpl categoryRepository;

    public ProductsRepositoryImpl() {
    }

    public ProductsRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void setConnection(Connection conn) {
        this.conn= conn;
    }

    @Override
    public List<Product> listAll() throws SQLException {
        List<Product> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT p.*, c.name as category FROM products as p " +
                     "INNER JOIN categories as c ON (p.id_categories = c.id_category) ");){
            while (resultSet.next()){
                list.add(createProduct(resultSet));
            }
        }
        return list;
    }

    @Override
    public Product find(Product product) throws SQLException {
        Category category = product.getCategory();
        Product foundProduct = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products as p WHERE p.id_products=?")){
            preparedStatement.setLong(1, category.getIdCategory());
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundProduct = createProduct(resultSet);
                }
            }
        }
        return foundProduct;
    }

    @Override
    public Product findByName(Product product) throws SQLException {
        Product foundProduct = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products as p WHERE p.name=?")){
            preparedStatement.setString(1, product.getName());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    foundProduct = createProduct(resultSet);
                }
            }
        }
        return foundProduct;
    }

    @Override
    public Product save(Product product) throws SQLException {
        String sql = null;
        if (product.getIdProduct() != null && product.getIdProduct() > 0){
            sql = "UPDATE products SET name=?, price=?, stock=?, id_categories=?  WHERE id_products=? ";
        }else {
            sql = "INSERT INTO products(name, price, stock, id_categories, registry_date) VALUES(?, ?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setString(4, product.getCategory().getName());
            if (product.getIdProduct() != null && product.getIdProduct() > 0){
                preparedStatement.setLong(5, product.getIdProduct());
            }else {
                preparedStatement.setDate(5, new Date(product.getRegistryDate().getTime()));
            }
            preparedStatement.executeUpdate();
            if (product.getIdProduct() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        product.setIdProduct(resultSet.getLong(1));
                    }
                }
            }
        }
        return product;
    }

    @Override
    public void deleted(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id_products=?")){
            preparedStatement.setLong(1, product.getIdProduct());
            preparedStatement.executeUpdate();
        }
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setIdProduct(resultSet.getLong("id_products"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getLong("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setRegistryDate(resultSet.getDate("registry_date"));
        Category category = new Category();
        category.setIdCategory(resultSet.getLong("id_categories"));
        try{
        category.setName(resultSet.getString("category"));
        }catch (SQLException e){
        }
        product.setCategory(category);
        return product;
    }
}
