package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Admin;

import java.util.List;

public interface AdminService {

    Admin findByIdAdmin(Long id);

    Admin findByUsername(String username);

    List<Admin> findAll ();

    void save(Admin admin);

    void delete(Admin admin);
}
