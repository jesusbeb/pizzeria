package com.jbeb.pizza.persistence.repository;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

// Extendemos y se le indica la Entidad y el tipo de dato de su clave primaria
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    // Consulta todas las pizzas que estan disponibles y las ordena por precio
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    // Consulta una pizza por nombre y que este disponible
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    // Consulta todas las pizzas con un ingrediente especificado
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    // Consulta que cuenta el numero de pizzas veganas y retonar un entero
    int countByVeganTrue();

}
