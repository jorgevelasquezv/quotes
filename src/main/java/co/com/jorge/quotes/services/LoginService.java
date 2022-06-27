package co.com.jorge.quotes.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {

    Optional<String> getUsernameAdmin(HttpServletRequest request);
    Optional<String> getUsernameProvider(HttpServletRequest request);

}
