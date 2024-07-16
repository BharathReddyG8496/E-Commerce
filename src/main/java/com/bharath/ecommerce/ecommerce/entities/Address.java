package com.bharath.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Address {
    /** Unique id for the address. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long addressId;
    /** The first line of address. */
    @Column(name = "address_line_1", nullable = false, length = 512)
    @NotNull
    @NotBlank
    private String addressLine1;
    /** The second line of address. */
    @Column(name = "address_line_2", length = 512)
    private String addressLine2;
    /** The city of the address. */
    @Column(name = "city", nullable = false)
    @NotNull
    @NotBlank
    private String city;
    /** The country of the address. */
    @Column(name = "country", nullable = false, length = 75)
    @NotNull
    @NotBlank
    private String country;
    /** The user the address is associated with. */
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private LocalUser user;

}
