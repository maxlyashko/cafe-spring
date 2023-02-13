package ua.lyashko.cafe.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lyashko.cafe.model.Category;
import ua.lyashko.cafe.service.CategoryService;

@Controller
@Log4j2
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController ( CategoryService categoryService ) {
        super ( );
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/new")
    public String createCategoryForm ( Model model ) {
        model.addAttribute ( "category" , new Category ( ) );
        log.info ( "category model logged" );
        return "create_category";
    }

    @PostMapping("/categories")
    public String addCategory ( @ModelAttribute Category category ) {
        if (category == null) {
            return "create_category";
        }
        categoryService.saveCategory ( category );
        log.info ( "category model logged and saved: " + category );
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String start ( Model model ) {
        model.addAttribute ( "categories" , categoryService.getAllCategories ( ) );
        log.info ( "category list logged: " + categoryService.getAllCategories () );
        return "categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategoryForm ( @PathVariable Integer id , Model model ) {
        model.addAttribute ( "category" , categoryService.getCategoryById ( id ) );
        log.info ( "category edit logged" );
        return "edit_category";
    }

    @PostMapping("/categories/{id}")
    public String editCategory ( @PathVariable Integer id,
                                  @ModelAttribute("category") Category category,
                                  Model model ) {
        Category existingCategory = categoryService.getCategoryById ( id );
        existingCategory.setId ( id );
        existingCategory.setName ( category.getName () );
        categoryService.updateCategory ( existingCategory );
        log.info ( "category edit by id logged: " + existingCategory );
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategoryById ( id );
        log.info ( "category deleted by id: " + id );
        return "redirect:/categories";
    }
}
