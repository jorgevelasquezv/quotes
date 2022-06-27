package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.RequestProduct;

import java.util.List;

public interface RequestProductService {

    RequestProduct findById(Long id);

    List<RequestProduct> find();

    void save(RequestProduct requestProduct);

    void delete(RequestProduct requestProduct);

}
