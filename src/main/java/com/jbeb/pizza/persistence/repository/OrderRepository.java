package com.jbeb.pizza.persistence.repository;

import com.jbeb.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

// Extendemos e indicamos el nombre de la Entidad a trabajar y el tipo de su clave primaria
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}
