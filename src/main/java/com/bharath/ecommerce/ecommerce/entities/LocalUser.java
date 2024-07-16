package com.bharath.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LocalUser {
    /** Unique id for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The username of the user. */
    @Column(name = "username", nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String username;
    /** The encrypted password of the user. */
//    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    @NotNull
    @NotBlank
    private String password;
    /** The email of the user. */
    @Column(name = "email", nullable = false, unique = true, length = 320)
    @Email
    private String email;
    /** The first name of the user. */
    @Column(name = "first_name", nullable = false)
    @NotNull
    @NotBlank
    private String firstName;
    /** The last name of the user. */
    @Column(name = "last_name", nullable = false)
    @NotNull
    @NotBlank
    private String lastName;
    /** The addresses associated with the user. */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)//By default, This annotation is lazy, so manually set it to eager
    private List<String> roles;

}
