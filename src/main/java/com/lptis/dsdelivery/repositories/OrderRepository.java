package com.lptis.dsdelivery.repositories;

import com.lptis.dsdelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
