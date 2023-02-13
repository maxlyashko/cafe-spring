package ua.lyashko.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.cafe.model.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer> {
}
