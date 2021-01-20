package com.lptis.dsdelivery.repositories;

import com.lptis.dsdelivery.entities.Order;
import com.lptis.dsdelivery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT DISTINCT orders FROM Order orders JOIN FETCH orders.products  " +
            " WHERE orders.status = 0 ORDER BY orders.moment ASC ")
    List<Order> findPendingOrdersWithProducts();
}
