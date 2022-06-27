package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Category;
import co.com.jorge.quotes.repositories.CategoryRepositoryImpl;
import co.com.jorge.quotes.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    private Connection conn;

    private Repository<Category> categoryRepository;

    public CategoryServiceImpl(Connection conn) {
        this.conn = conn;
        this.categoryRepository = new CategoryRepositoryImpl();
    }

    @Override
    public Category findById(Long id) {
        Category category = new Category();
        category.setIdCategory(id);

        categoryRepository.setConnection(conn);

        try {
            category = categoryRepository.findById(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category findByName(String name) {
        Category category = new Category();
        category.setName(name);

        categoryRepository.setConnection(conn);

        try {
            category = categoryRepository.findByName(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();

        categoryRepository.setConnection(conn);
        try {
            categoryList = categoryRepository.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void save(Category category) {
        categoryRepository.setConnection(conn);
        try {
            categoryRepository.save(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) {
        categoryRepository.setConnection(conn);
        try {
            categoryRepository.delete(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
