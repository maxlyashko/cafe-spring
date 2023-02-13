package ua.lyashko.cafe.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.lyashko.cafe.service.BillService;
import ua.lyashko.cafe.service.CategoryService;
import ua.lyashko.cafe.service.ProductService;

@Controller
@Log4j2
public class DashboardController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BillService billService;

    public DashboardController ( ProductService productService , CategoryService categoryService , BillService billService ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.billService = billService;
    }

    @GetMapping("/dashboard")
    public String getDashboard ( Model model ) {
        model.addAttribute ( "categories" , categoryService.getAllCategories ( ).size ( ) );
        model.addAttribute ( "products" , productService.getAllProducts ( ).size ( ) );
        model.addAttribute ( "bills" , billService.getALlBills ( ).size ( ) );
        log.info ( "dashboard model logged" );
        return "dashboard";
    }

    @GetMapping("/")
    public String start ( Model model ) {
        model.addAttribute ( "categories" , categoryService.getAllCategories ( ).size ( ) );
        model.addAttribute ( "products" , productService.getAllProducts ( ).size ( ) );
        model.addAttribute ( "bills" , billService.getALlBills ( ).size ( ) );
        log.info ( "dashboard model logged" );
        return "dashboard";
    }
}
