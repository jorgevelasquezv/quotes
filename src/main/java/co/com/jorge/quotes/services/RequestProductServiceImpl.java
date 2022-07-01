package co.com.jorge.quotes.services;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequestProductServiceImpl implements RequestProductService{

    @Inject
    private Repository<RequestProduct> requestProductRepository;

    @Override
    public RequestProduct findById(Long id) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setIdRequest(id);
        try {
            requestProduct = requestProductRepository.findById(requestProduct);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return requestProduct;
    }

    @Override
    public List<RequestProduct> find() {
        List<RequestProduct> requestProductList = new ArrayList<>();
        try {
            requestProductList = requestProductRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return requestProductList;
    }

    @Override
    public void save(RequestProduct requestProduct) {
        try {
            requestProductRepository.save(requestProduct);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(RequestProduct requestProduct) {
        try {
            requestProductRepository.delete(requestProduct);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
