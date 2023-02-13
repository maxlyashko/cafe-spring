package ua.lyashko.cafe.service;


import ua.lyashko.cafe.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts ();

    Product saveProduct ( Product product );

    Product getProductById ( Integer id );

    Product updateProduct ( Product product );

    void deleteProductById ( Integer id );
}
