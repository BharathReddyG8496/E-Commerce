package com.bharath.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cart {
    /** Unique id for the order. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long cartId;
    /** The user of the order. */
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;
    /** The shipping address of the order. */
    @OneToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @ToString.Exclude
    private List<OrderQuantities> orderQuantities = new ArrayList<>();



}
