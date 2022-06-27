package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Provider;
import co.com.jorge.quotes.repositories.ProviderRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderServiceImpl implements ProviderService{

    private Connection conn;

    private Repository<Provider> providerRepository;

    public ProviderServiceImpl(Connection conn) {
        this.conn = conn;
        this.providerRepository = new ProviderRepositoryImpl();
    }

    @Override
    public Provider findById(Long id) {
        Provider provider = new Provider();
        provider.setIdProvider(id);
        providerRepository.setConnection(conn);
        try {
            provider = providerRepository.findById(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public Provider findByName(String username) {
        Provider provider = new Provider();
        provider.setUsername(username);
        providerRepository.setConnection(conn);
        try {
            provider = providerRepository.findByName(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public List<Provider> find() {
        List<Provider> providerList = new ArrayList<>();
        providerRepository.setConnection(conn);
        try {
            providerList = providerRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerList;
    }

    @Override
    public void save(Provider provider) {
        providerRepository.setConnection(conn);
        try {
            providerRepository.save(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Provider provider) {
        providerRepository.setConnection(conn);
        try {
            providerRepository.delete(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
