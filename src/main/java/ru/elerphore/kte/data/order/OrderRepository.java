package ru.elerphore.kte.data.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elerphore.kte.data.customer.CustomerEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
}
