package com.bharath.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
    /** Unique id for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** The name of the product. */
    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String productName;

    /** The short description of the product. */
    @Column(name = "short_description", nullable = false)
    @NotNull
    @NotBlank
    private String shortDescription;

    /** The long description of the product. */
    @Column(name = "long_description")
    @NotNull
    @NotBlank
    private String longDescription;

    /** The price of the product. */
    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    /** The inventory of the product. */
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JsonIgnore
    @JsonManagedReference
    private Inventory inventory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    @JsonBackReference
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;
}