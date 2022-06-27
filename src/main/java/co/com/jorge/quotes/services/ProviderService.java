package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Provider;

import java.util.List;

public interface ProviderService {

    Provider findById(Long id);

    Provider findByName(String username);

    List<Provider> find();

    void save(Provider provider);

    void delete(Provider provider);
}
