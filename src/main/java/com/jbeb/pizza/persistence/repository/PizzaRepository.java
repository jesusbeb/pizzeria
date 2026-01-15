package com.jbeb.pizza.persistence.repository;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

// Extendemos y se le indica la Entidad y el tipo de dato de su clave primaria
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    // Consulta todas las pizzas que estan disponibles y las ordena por precio
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    // Consulta una pizza por nombre y que este disponible
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

}
