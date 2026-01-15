package com.jbeb.pizza.persistence.repository;

import com.jbeb.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

// Extendemos e indicamos el nombre de la Entidad a trabajar y el tipo de su clave primaria
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    // Consultar todas las ordenes despues de una fecha especifica
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    // Consultar ordenes por el atributo method que indica si la orden se consumio en el negocio, para llevar o para entrega
    // Como se usa el keyword "In" se debe recibir un List de tipo String que es el tipo del atributo method
    List<OrderEntity> findAllByMethodIn(List<String> methods);

}
