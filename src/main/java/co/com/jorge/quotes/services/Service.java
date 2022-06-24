package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.*;

import java.util.List;

public interface Service {

    Admin adminFindByUsername (String username);

    Provider providerFindByUsername(String username);

    List<Product > productFindAll();

    Product productFindByName(String name);

    List<RequestProduct> requestProductFindAll();

    List<Offer> offertFindAll();

    void requestProduct(RequestProduct requestProduct);
}
