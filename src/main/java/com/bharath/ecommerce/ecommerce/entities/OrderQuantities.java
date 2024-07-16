package com.bharath.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderQuantities {
    /** The unqiue id of the order quantity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long orderQuantitiesId;
    /** The product being ordered. */
    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    /** The quantity being ordered. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /** The order itself. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


}
