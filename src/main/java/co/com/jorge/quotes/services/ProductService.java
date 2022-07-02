package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    Product findByName(String name);

    List<Product> find();

    void save(Product product);

    void delete(Product product);

}
