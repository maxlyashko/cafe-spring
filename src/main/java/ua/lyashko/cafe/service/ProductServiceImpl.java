package ua.lyashko.cafe.service;

import org.springframework.stereotype.Service;
import ua.lyashko.cafe.model.Product;
import ua.lyashko.cafe.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl ( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts () {
        return (List<Product>) productRepository.findAll ( );
    }

    @Override
    public Product saveProduct ( Product product ) {
        return productRepository.save ( product );
    }

    @Override
    public Product getProductById ( Integer id ) {
        return productRepository.findById ( id ).get ( );
    }

    @Override
    public Product updateProduct ( Product product ) {
        return productRepository.save ( product );
    }

    @Override
    public void deleteProductById ( Integer id ) {
        productRepository.deleteById ( id );
    }
}
