package com.lptis.dsdelivery.controllers;

import com.lptis.dsdelivery.dto.OrderDTO;
import com.lptis.dsdelivery.dto.ProductDTO;
import com.lptis.dsdelivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Return All Orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Return All Pending Orders
    @GetMapping(value = "/pending")
    public ResponseEntity<List<OrderDTO>> findAllPending(){
        List<OrderDTO> list = orderService.findAllPending();
        return ResponseEntity.ok().body(list);
    }

    //Insert Order
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO){
        orderDTO = orderService.insert(orderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }

    //Set Order Status Delivered
    @PutMapping(value = "/{id}/delivered")
    public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.setDelivered(id);
        return ResponseEntity.ok().body(orderDTO);
    }

    //Delete Order
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.delete(id);
        return ResponseEntity.ok().body("Order: " + orderDTO.getId() + " removida com sucesso!");
    }

}
