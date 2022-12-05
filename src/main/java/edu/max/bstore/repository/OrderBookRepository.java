package edu.max.bstore.repository;

import edu.max.bstore.entity.OrderBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBookEntity, Long> {
}
