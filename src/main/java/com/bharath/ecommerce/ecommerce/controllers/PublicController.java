package com.bharath.ecommerce.ecommerce.controllers;

import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import com.bharath.ecommerce.ecommerce.entities.Product;
import com.bharath.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import com.bharath.ecommerce.ecommerce.services.UserServices;
import com.bharath.ecommerce.ecommerce.services.categoryServices;
import com.bharath.ecommerce.ecommerce.services.productServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private categoryServices categoryServices;

    @Autowired
    private productServices prodServices;

    @Autowired
    private UserServices userService;

    @GetMapping("/get_prods_by_cat/{category_name}")
    public ResponseEntity<Object> getProdsByCategory(@PathVariable String category_name){
        List<Product> productsByCategoryName = categoryServices.getProductsByCategoryName(category_name);
        if (productsByCategoryName==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(productsByCategoryName,HttpStatus.OK);
    }

    @GetMapping("/getRandomProducts/number")
    public ResponseEntity<Object> getRandomProducts(@RequestParam(defaultValue = "10") int count){
        return new ResponseEntity<>(prodServices.getRandomProducts(count),HttpStatus.OK);
    }
    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody LocalUser user){
        try {
            System.out.println(user);
            LocalUser localUser = userService.registerUser(user);
            if (localUser == null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            else
                return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }



}
