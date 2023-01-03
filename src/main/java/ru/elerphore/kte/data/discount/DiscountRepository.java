package ru.elerphore.kte.data.discount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends CrudRepository<DiscountEntity, Long> {
}
