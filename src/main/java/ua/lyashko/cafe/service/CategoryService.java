package ua.lyashko.cafe.service;

import ua.lyashko.cafe.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category saveCategory(Category category);

    Category getCategoryById(Integer id);

    Category updateCategory(Category category);

    void deleteCategoryById(Integer id);
}
