package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Product;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private Repository<Product> productRepository;

    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setIdProduct(id);
        try {
            product = productRepository.findById(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        Product product = new Product();
        product.setName(name);
        try {
            product = productRepository.findByName(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return product;
    }

    @Override
    public List<Product> find() {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return productList;
    }

    @Override
    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Product product) {
        try {
            productRepository.delete(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
