package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Admin;
import co.com.jorge.quotes.repositories.AdminRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdminServiceImpl implements AdminService{

    @Inject
    @Named("conn")
    private Connection conn;

    @Inject
    private Repository<Admin> adminRepository;


//    public AdminServiceImpl() {
//        this.conn = conn;
//        this.adminRepository = new AdminRepositoryImpl();
//    }

    @Override
    public Admin findByIdAdmin(Long id) {
        Admin admin = new Admin();
        admin.setIdAdmin(id);
        adminRepository.setConnection(conn);
        try {
            admin = adminRepository.findById(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin findByUsername(String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        adminRepository.setConnection(conn);
        try {
            admin = adminRepository.findByName(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        adminRepository.setConnection(conn);
        try {
            adminList = adminRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public void save(Admin admin) {
        adminRepository.setConnection(conn);
        try {
            adminRepository.save(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Admin admin) {
        adminRepository.setConnection(conn);
        try {
            adminRepository.delete(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
