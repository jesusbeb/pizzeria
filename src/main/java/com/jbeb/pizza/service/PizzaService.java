package com.jbeb.pizza.service;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import com.jbeb.pizza.persistence.repository.PizzaPagSortRepository;
import com.jbeb.pizza.persistence.repository.PizzaRepository;
import com.jbeb.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    // Como se anoto como final, se agrega como parametro en un constructor
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    //Constructor
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    // Metodo para consultar todas las pizzas. Retorna un Page (página)
    // PageRequest.of recibe el numero de pagina que queremos ver y el numero de elementos que se mostraran (las
    // paginas siempre empiezan desde cero)
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    // Metodo para consultar todas las pizzas disponibles. Retorna un Page
    // Recibe como parametro la pagina a consultar, el numero de elementos a mostrar, a traves de que propiedad se
    // ordenara y si sera ascendente o descendente.
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        System.out.println("Pizzas veganas disponibles: "+this.pizzaRepository.countByVeganTrue()); // Imprimimos en consola

        // sortBy recibe una direccion de ordenamiento (ASC o DESC) y la propiedad a traves de la cual se ordenara
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    // findById retorna un Optional, por lo que usamos orElse para indicar que retorne null si no encuentra nada
    public PizzaEntity getById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String name){
        // Otras formas de retornar un Pizza Entity cuando se recibe un Optional
        // return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElse(null); // retornar un null si no se encuentra nada
        // return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(); // Lanzar una excepcion
        // Lanzamos una excepcion con programacion funcional e imprimimos en consola el error
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow( () -> new RuntimeException("La pizza no existe") );
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    // Metodo que retorna las tres pizzas mas baratas de un precio que se indique
    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    // @Transactional
    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto.getPizzaId(), dto.getNewPrice());
       //this.pizzaRepository.updatePrice(updatePizzaPriceDto); // se envia el dto en caso de usar el metodo que usa Spring Expression Language
    }

    // Metodo para comprobar si una pizza ya existe
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

}
