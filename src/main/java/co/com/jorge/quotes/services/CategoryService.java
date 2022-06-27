package co.com.jorge.quotes.services;

import co.com.jorge.quotes.models.Category;

import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    Category findByName(String name);

    List<Category> findAll();

    void save(Category category);

    void delete(Category category);
}
