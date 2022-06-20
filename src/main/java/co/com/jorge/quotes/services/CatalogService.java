package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.models.Provider;
import co.com.jorge.quotes.repositories.AdminRepositoryImpl;
import co.com.jorge.quotes.repositories.ProviderRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;
import co.com.jorge.quotes.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class CatalogService implements Service {

    private Repository<Admin> adminRepository;

    private Repository<Provider> providerRepository;

    public CatalogService() {
        this.adminRepository = new AdminRepositoryImpl();
        this.providerRepository = new ProviderRepositoryImpl();
    }

    @Override
    public Admin adminFindByUsername(String username){
        Admin admin = new Admin();
        admin.setUsername(username);
        try (Connection conn = DataBaseConnection.getConnection()){
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
        try (Connection conn = DataBaseConnection.getConnection()){
            providerRepository.setConnection(conn);
            provider = providerRepository.find(provider);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return provider;
    }
}
