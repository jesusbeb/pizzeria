package com.jbeb.pizza.service;

import com.jbeb.pizza.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    // JdbcTemplate para hacer consultas a la BD
    // Como se anoto como final, se agrega como parametro en un constructor
    private final JdbcTemplate jdbcTemplate;

    //Constructor
    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metodo para consultar todas las pizzas
    // query() permite escribir consultas SQL desde Java y convertir el resultado en una clase Java
    public List<PizzaEntity> getAll(){
        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }

}
