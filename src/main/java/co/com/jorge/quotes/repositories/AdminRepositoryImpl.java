package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.models.Admin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdminRepositoryImpl implements Repository<Admin> {

    @Inject
    @Named("conn")
    private Connection conn;

    public AdminRepositoryImpl() {
    }

    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Admin> listAll() throws SQLException {
        List<Admin> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM admin");){
            while (resultSet.next()){
                list.add(createAdmin(resultSet));
            }
        }
        return list;
    }

    @Override
    public Admin findById(Admin admin) throws SQLException {
        Long id = admin.getIdAdmin();
        Admin foundAdmin = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM admins as a WHERE a.id_admins=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundAdmin = createAdmin(resultSet);
                }
            }
        }
        return foundAdmin;
    }

    @Override
    public Admin findByName(Admin admin) throws SQLException {
        String username = admin.getUsername();
        Admin foundAdmin = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM admins as a WHERE a.username=?")){
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundAdmin = createAdmin(resultSet);
                }
            }
        }
        return foundAdmin;
    }

    @Override
    public Admin save(Admin admin) throws SQLException {
        String sql = null;
        if (admin.getIdAdmin() != null && admin.getIdAdmin() > 0){
            sql = "UPDATE admins SET username=?, password=?, WHERE id_admins=? ";
        }else {
            sql = "INSERT INTO admins(username, password) VALUES(?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            if (admin.getIdAdmin() != null && admin.getIdAdmin() > 0){
                preparedStatement.setLong(3, admin.getIdAdmin());
            }
            preparedStatement.executeUpdate();
            if (admin.getIdAdmin() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        admin.setIdAdmin(resultSet.getLong(1));
                    }
                }
            }
        }
        return admin;
    }

    @Override
    public void delete(Admin admin) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM admins WHERE id_admins=?")){
            preparedStatement.setLong(1, admin.getIdAdmin());
            preparedStatement.executeUpdate();
        }
    }

    private Admin createAdmin(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setIdAdmin(resultSet.getLong("id_admins"));
        admin.setUsername(resultSet.getString("username"));
        admin.setPassword(resultSet.getString("password"));
        return admin;
    }
}
