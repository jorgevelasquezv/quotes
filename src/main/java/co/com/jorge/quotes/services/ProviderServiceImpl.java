package co.com.jorge.quotes.services;

import co.com.jorge.quotes.config.Service;
import co.com.jorge.quotes.models.Provider;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService{

    @Inject
    private Repository<Provider> providerRepository;

    @Override
    public Provider findById(Long id) {
        Provider provider = new Provider();
        provider.setIdProvider(id);
        try {
            provider = providerRepository.findById(provider);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return provider;
    }

    @Override
    public Provider findByName(String username) {
        Provider provider = new Provider();
        provider.setUsername(username);
        try {
            provider = providerRepository.findByName(provider);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return provider;
    }

    @Override
    public List<Provider> find() {
        List<Provider> providerList = new ArrayList<>();
        try {
            providerList = providerRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return providerList;
    }

    @Override
    public void save(Provider provider) {
        try {
            providerRepository.save(provider);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Provider provider) {
        try {
            providerRepository.delete(provider);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
