package ua.lyashko.cafe.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lyashko.cafe.model.Product;
import ua.lyashko.cafe.service.CategoryService;
import ua.lyashko.cafe.service.ProductService;


@Controller
@Log4j2
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController ( ProductService productService ,
                               CategoryService categoryService ) {
        super ( );
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/new")
    public String createProductForm ( Model model ) {
        model.addAttribute ( "product" , new Product ( ) );
        model.addAttribute ( "categories" , categoryService.getAllCategories ( ) );
        log.info ( "product model logged" );
        return "create_product";
    }

    @PostMapping("/products")
    public String addProduct ( @ModelAttribute Product product ) {
        if (product == null) {
            return "create_product";
        }
        productService.saveProduct ( product );
        log.info ( "product model logged and saved: " + product );
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String start ( Model model ) {
        model.addAttribute ( "products" , productService.getAllProducts ( ) );
        log.info ( "product list logged: " + productService.getAllProducts () );
        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm ( @PathVariable Integer id , Model model ) {
        model.addAttribute ( "product" , productService.getProductById ( id ) );
        model.addAttribute ( "categories" , categoryService.getAllCategories ( ) );
        log.info ( "product edit logged" );
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String editProduct ( @PathVariable Integer id ,
                                @ModelAttribute("product") Product product ,
                                Model model ) {
        Product existingProduct = productService.getProductById ( id );
        existingProduct.setId ( id );
        existingProduct.setName ( product.getName ( ) );
        existingProduct.setCategory ( product.getCategory ( ) );
        existingProduct.setPrice ( product.getPrice ( ) );
        productService.updateProduct ( existingProduct );
        log.info ( "product edit by id logged: " + existingProduct );
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String deleteProduct ( @PathVariable Integer id ) {
        productService.deleteProductById ( id );
        log.info ( "product deleted by id: " + id );
        return "redirect:/products";
    }
}
