package co.com.jorge.quotes.repositories;

import co.com.jorge.quotes.config.ConnectionMySQL;
import co.com.jorge.quotes.config.RepositoryApp;
import co.com.jorge.quotes.models.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryApp
public class CategoryRepositoryImpl implements Repository<Category>{

    @Inject
    @ConnectionMySQL
    private Connection conn;

    public CategoryRepositoryImpl() {
    }


    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Category> listAll() throws SQLException {

        List<Category> list = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");){
            while (resultSet.next()){
                list.add(createCategory(resultSet));
            }
        }
        return list;
    }

    @Override
    public Category findById(Category category) throws SQLException {
        Long id = category.getIdCategory();
        Category foundCategory = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM categories as c WHERE c.id_category=?")){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundCategory = createCategory(resultSet);
                }
            }
        }
        return foundCategory;
    }


    @Override
    public Category findByName(Category category) throws SQLException {
        String name = category.getName();
        Category foundCategory = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM categories as c WHERE c.name=?")){
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    foundCategory = createCategory(resultSet);
                }
            }
        }
        return foundCategory;
    }

    @Override
    public Category save(Category category) throws SQLException {
        String sql = null;
        if (category.getIdCategory() != null && category.getIdCategory() > 0){
            sql = "UPDATE categories SET name=? WHERE id_category=? ";
        }else {
            sql = "INSERT INTO categories(name) VALUES(?)";
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, category.getName());
            if (category.getIdCategory() != null && category.getIdCategory() > 0){
                preparedStatement.setLong(2, category.getIdCategory());
            }
            preparedStatement.executeUpdate();
            if (category.getIdCategory() == null){
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if (resultSet.next()){
                        category.setIdCategory(resultSet.getLong(1));
                    }
                }
            }
        }
        return category;
    }

    @Override
    public void delete(Category category) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM categories WHERE id_category=?")){
            preparedStatement.setLong(1, category.getIdCategory());
            preparedStatement.executeUpdate();
        }
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setIdCategory(resultSet.getLong("id_categories"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
