package com.bharath.ecommerce.ecommerce.controllers;

import com.bharath.ecommerce.ecommerce.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userService;
//Things the user can do,if he is logged in
    //add,delete and update addresses
    //add products to cart
    //choose various categories
    //change his user details
    //search for products
    //checkout

    @DeleteMapping("/DeleteAccount")
    public ResponseEntity<Object> deleteUserByUsername(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        boolean b = userService.DeleteUserByUsername(username);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }







}
