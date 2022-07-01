package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService{

    @Inject
    private Repository<Category> categoryRepository;

    @Override
    public Category findById(Long id) {
        Category category = new Category();
        category.setIdCategory(id);

        try {
            category = categoryRepository.findById(category);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return category;
    }

    @Override
    public Category findByName(String name) {
        Category category = new Category();
        category.setName(name);
        try {
            category = categoryRepository.findByName(category);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();

        try {
            categoryList = categoryRepository.listAll();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
        return categoryList;
    }

    @Override
    public void save(Category category) {
        try {
            categoryRepository.save(category);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(Category category) {
        try {
            categoryRepository.delete(category);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
