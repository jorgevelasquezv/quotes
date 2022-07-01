package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.config.RepositoryApp;
import co.com.jorge.quotes.models.Provider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryApp
public class ProviderRepositoryImpl implements Repository<Provider>{

    @Inject
    @ConnectionMySQL
    private Connection conn;

    public ProviderRepositoryImpl() {
    }

    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Provider> listAll() throws SQLException {
        List<Provider> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM providers");){
            while (resultSet.next()){
                list.add(createProvider(resultSet));
            }
        }
        return list;
    }

    @Override
    public Provider findById(Provider provider) throws SQLException {
        Long id = provider.getIdProvider();
        Provider foundProvider = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM providers as p WHERE p.id_providers=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundProvider = createProvider(resultSet);
                }
            }
        }
        return foundProvider;
    }

    @Override
    public Provider findByName(Provider provider) throws SQLException {
        String username = provider.getUsername();
        Provider foundProvider = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM providers as p WHERE p.username=?")){
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundProvider = createProvider(resultSet);
                }
            }
        }
        return foundProvider;
    }

    @Override
    public Provider save(Provider provider) throws SQLException {
        String sql = null;
        if (provider.getIdProvider() != null && provider.getIdProvider() > 0){
            sql = "UPDATE providers SET username=?, password=?, name=?, phone=?, address=?, nit=? WHERE id_providers=? ";
        }else {
            sql = "INSERT INTO providers(username, password, name, address, phone, nit) VALUES(?, ? , ?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, provider.getUsername());
            preparedStatement.setString(2, provider.getPassword());
            preparedStatement.setString(3, provider.getName());
            preparedStatement.setString(4, provider.getAddress());
            preparedStatement.setString(5, provider.getPhone());
            preparedStatement.setInt(6, provider.getNit());
            if (provider.getIdProvider() != null && provider.getIdProvider() > 0){
                preparedStatement.setLong(7, provider.getIdProvider());
            }
            preparedStatement.executeUpdate();
            if (provider.getIdProvider() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        provider.setIdProvider(resultSet.getLong(1));
                    }
                }
            }
        }
        return provider;
    }

    @Override
    public void delete(Provider provider) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM providers WHERE id_providers=?")){
            preparedStatement.setLong(1, provider.getIdProvider());
            preparedStatement.executeUpdate();
        }
    }

    private Provider createProvider(ResultSet resultSet) throws SQLException {
        Provider provider = new Provider();
        provider.setIdProvider(resultSet.getLong("id_providers"));
        provider.setUsername(resultSet.getString("username"));
        provider.setPassword(resultSet.getString("password"));
        provider.setName(resultSet.getString("name"));
        provider.setAddress(resultSet.getString("address"));
        provider.setPhone(resultSet.getString("phone"));
        provider.setNit(resultSet.getInt("nit"));
        return provider;
    }
}
