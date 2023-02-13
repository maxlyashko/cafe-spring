package ua.lyashko.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.cafe.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
