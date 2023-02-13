package ua.lyashko.cafe.service;

import org.springframework.stereotype.Service;
import ua.lyashko.cafe.model.Category;
import ua.lyashko.cafe.repository.CategoryRepository;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl ( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories () {
        return (List<Category>) categoryRepository.findAll ( );
    }

    @Override
    public Category saveCategory ( Category category ) {
        return categoryRepository.save ( category );
    }

    @Override
    public Category getCategoryById ( Integer id ) {
        return categoryRepository.findById ( id ).get ( );
    }

    @Override
    public Category updateCategory ( Category category ) {
        return categoryRepository.save ( category );
    }

    @Override
    public void deleteCategoryById ( Integer id ) {
        categoryRepository.deleteById ( id );
    }
}
