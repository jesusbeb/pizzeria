package com.jbeb.pizza.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double total;

    // CHAR(1) para especificar que en la BD se cree de tamaño maximo 256
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;


    // Relacion de OrderEntity con CustomerEntity
    // OneToOne. Una orden solo tiene un cliente
    @OneToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    private CustomerEntity customer;

    // Relacion de OrderEntity con OrderItemEntity
    // OneToMany. Muchos item pueden estar en una sola orden. Por lo que se usa un List de OrderItemEntity
    // mappedBy se indica el nombre del atributo donde esta la otra relacion ManyToOne con este Entity
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> items;

}
