package com.bharath.ecommerce.ecommerce.controllers;

import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import com.bharath.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import com.bharath.ecommerce.ecommerce.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserServices userService;



}
