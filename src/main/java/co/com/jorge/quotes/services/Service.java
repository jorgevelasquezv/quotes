package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.models.Provider;

public interface Service {

    Admin adminFindByUsername (String username);

    Provider providerFindByUsername(String username);
}
