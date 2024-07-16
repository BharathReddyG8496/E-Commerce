package com.bharath.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "category")
public class Category {
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonManagedReference
    private List<Product> catProducts = new ArrayList<>();

    @Column(name = "category_name", nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String categoryName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "cat_image_url")
    private String imageUrl;

}