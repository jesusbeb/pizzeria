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

    // findById retorna un Optional, por lo que usamos orElse para indicar que retorne null si no encuentra nada
    public PizzaEntity getById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
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
