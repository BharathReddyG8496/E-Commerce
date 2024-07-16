package com.bharath.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Inventory {
    /** Unique id for the inventory. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long inventoryId;
    /** The product this inventory is of. */
    @JsonIgnore
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    @JsonBackReference
    private Product product;
    /** The quantity in stock. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}