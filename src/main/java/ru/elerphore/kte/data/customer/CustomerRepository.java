package ru.elerphore.kte.data.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    @Override
    public List<CustomerEntity> findAll();
}
