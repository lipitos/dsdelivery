package com.lptis.dsdelivery.repositories;

import com.lptis.dsdelivery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
