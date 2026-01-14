package com.jbeb.pizza.persistence.repository;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

// Extendemos y se le indica la Entidad y el tipo de dato de su clave primaria
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
}
