package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.*;
import co.com.jorge.quotes.repositories.*;
import co.com.jorge.quotes.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogService implements Service {

    private Connection conn;

    private Repository<Admin> adminRepository;

    private Repository<Provider> providerRepository;

    private Repository<Product> productRepository;

    private Repository<RequestProduct> requestProductRepository;

    private Repository<Offer> offerRepository;


    public CatalogService(Connection conn) {
        this.conn = conn;
        this.adminRepository = new AdminRepositoryImpl();
        this.providerRepository = new ProviderRepositoryImpl();
        this.productRepository = new ProductsRepositoryImpl();
        this.requestProductRepository = new RequestProductRepositoryImpl();
        this.offerRepository = new OfferRepositoryImpl();
    }

    @Override
    public Admin adminFindByUsername(String username){
        Admin admin = new Admin();
        admin.setUsername(username);
        try {
            adminRepository.setConnection(conn);
            admin = adminRepository.find(admin);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public Provider providerFindByUsername(String username) {
        Provider provider = new Provider();
        provider.setUsername(username);
        try {
            providerRepository.setConnection(conn);
            provider = providerRepository.find(provider);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return provider;
    }

    @Override
    public List<Product> productFindAll() {
        List<Product> productList ;
        try {
            productRepository.setConnection(conn);
            productList = productRepository.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public Product productFindByName(String name) {
        Product product = new Product();
        product.setName(name);
        try {
            productRepository.setConnection(conn);
            product = productRepository.findByName(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<RequestProduct> requestProductFindAll() {
        List<RequestProduct> requestProductList;
        try {
            requestProductRepository.setConnection(conn);
            requestProductList = requestProductRepository.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requestProductList;
    }

    @Override
    public void requestProduct(RequestProduct requestProduct) {
        try {
            requestProductRepository.setConnection(conn);
            requestProductRepository.save(requestProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Offer> offertFindAll() {
        List<Offer> offerList;
        try {
            offerRepository.setConnection(conn);
            offerList = offerRepository.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return offerList;
    }
}
