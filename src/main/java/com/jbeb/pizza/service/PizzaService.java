package com.jbeb.pizza.service;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import com.jbeb.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    // Como se anoto como final, se agrega como parametro en un constructor
    private final PizzaRepository pizzaRepository;

    //Constructor
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    // Metodo para consultar todas las pizzas
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable(){
        System.out.println("Pizzas veganas disponibles: "+this.pizzaRepository.countByVeganTrue()); // Imprimimos en consola
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
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

    // Metodo para comprobar si una pizza ya existe
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

}
