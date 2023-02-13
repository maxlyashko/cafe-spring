package ua.lyashko.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.cafe.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
