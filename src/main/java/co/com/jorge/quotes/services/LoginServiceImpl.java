package co.com.jorge.quotes.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class LoginServiceImpl implements LoginService{

    @Override
    public Optional<String> getUsernameAdmin(HttpServletRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getUsernameProvider(HttpServletRequest request) {
        return Optional.empty();
    }
}
