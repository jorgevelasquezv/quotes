package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequestProductServiceImpl implements RequestProductService{

    @Inject
    @Named("conn")
    private Connection conn;

    @Inject
    private Repository<RequestProduct> requestProductRepository;

    @Override
    public RequestProduct findById(Long id) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setIdRequest(id);
        requestProductRepository.setConnection(conn);
        try {
            requestProduct = requestProductRepository.findById(requestProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestProduct;
    }

    @Override
    public List<RequestProduct> find() {
        List<RequestProduct> requestProductList = new ArrayList<>();
        requestProductRepository.setConnection(conn);
        try {
            requestProductList = requestProductRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestProductList;
    }

    @Override
    public void save(RequestProduct requestProduct) {
        requestProductRepository.setConnection(conn);
        try {
            requestProductRepository.save(requestProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(RequestProduct requestProduct) {
        requestProductRepository.setConnection(conn);
        try {
            requestProductRepository.delete(requestProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
