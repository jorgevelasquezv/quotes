package co.com.jorge.quotes.services;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdminServiceImpl implements AdminService{

    @Inject
    private Repository<Admin> adminRepository;

    @Override
    public Admin findByIdAdmin(Long id) {
        Admin admin = new Admin();
        admin.setIdAdmin(id);
        try {
            admin = adminRepository.findById(admin);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return admin;
    }

    @Override
    public Admin findByUsername(String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        try {
            admin = adminRepository.findByName(admin);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try {
            adminList = adminRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return adminList;
    }

    @Override
    public void save(Admin admin) {
        try {
            adminRepository.save(admin);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Admin admin) {
        try {
            adminRepository.delete(admin);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
