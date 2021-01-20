package com.lptis.dsdelivery.services;

import com.lptis.dsdelivery.dto.OrderDTO;
import com.lptis.dsdelivery.dto.ProductDTO;
import com.lptis.dsdelivery.entities.Order;
import com.lptis.dsdelivery.entities.Product;
import com.lptis.dsdelivery.repositories.OrderRepository;
import com.lptis.dsdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> list = orderRepository.findPendingOrdersWithProducts();
        return list.stream().map(order -> new OrderDTO((order))).collect(Collectors.toList());
    }

}
