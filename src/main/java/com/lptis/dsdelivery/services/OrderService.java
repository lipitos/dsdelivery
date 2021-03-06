package com.lptis.dsdelivery.services;

import com.lptis.dsdelivery.dto.OrderDTO;
import com.lptis.dsdelivery.dto.ProductDTO;
import com.lptis.dsdelivery.entities.Order;
import com.lptis.dsdelivery.entities.OrderStatus;
import com.lptis.dsdelivery.entities.Product;
import com.lptis.dsdelivery.repositories.OrderRepository;
import com.lptis.dsdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> list = orderRepository.findAll();
        return list.stream().map(order -> new OrderDTO((order))).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAllPending(){
        List<Order> list = orderRepository.findPendingOrdersWithProducts();
        return list.stream().map(order -> new OrderDTO((order))).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
                Instant.now(), OrderStatus.PENDING);
        for(ProductDTO p : dto.getProducts()){
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id){
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO delete(Long id){
        Order order = orderRepository.getOne(id);
        orderRepository.delete(order);
        return new OrderDTO(order);
    }

}
