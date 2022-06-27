package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.RequestProduct;
import co.com.jorge.quotes.repositories.Repository;
import co.com.jorge.quotes.repositories.RequestProductRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestProductServiceImpl implements RequestProductService{

    private Connection conn;

    private Repository<RequestProduct> requestProductRepository;

    public RequestProductServiceImpl(Connection conn) {
        this.conn = conn;
        this.requestProductRepository = new RequestProductRepositoryImpl();
    }

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
