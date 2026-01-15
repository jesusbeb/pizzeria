package com.jbeb.pizza.service;

import com.jbeb.pizza.persistence.entity.OrderEntity;
import com.jbeb.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // Con forEach imprimimos en consola los nombres de los clientes, ya que en la relacion de OrderEntity con
    // Customer se tiene FetchType.LAZY y no trae automaticamente la informacion del Customer
    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }
}
