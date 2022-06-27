package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.repositories.ProductsRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private Connection conn;

    private Repository<Product> productRepository;

    public ProductServiceImpl(Connection conn) {
        this.conn = conn;
        this.productRepository = new ProductsRepositoryImpl();
    }

    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setIdProduct(id);
        productRepository.setConnection(conn);
        try {
            product = productRepository.findById(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        Product product = new Product();
        product.setName(name);
        productRepository.setConnection(conn);
        try {
            product = productRepository.findByName(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> find() {
        List<Product> productList = new ArrayList<>();
        productRepository.setConnection(conn);
        try {
            productList = productRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void save(Product product) {
        productRepository.setConnection(conn);
        try {
            productRepository.save(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product) {
        productRepository.setConnection(conn);
        try {
            productRepository.delete(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
