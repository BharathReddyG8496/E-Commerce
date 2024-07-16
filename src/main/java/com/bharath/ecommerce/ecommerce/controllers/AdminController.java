package com.bharath.ecommerce.ecommerce.controllers;

import com.bharath.ecommerce.ecommerce.entities.Category;
import com.bharath.ecommerce.ecommerce.entities.DTOs.ProductDTO;
import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import com.bharath.ecommerce.ecommerce.entities.Product;
import com.bharath.ecommerce.ecommerce.services.UserServices;
import com.bharath.ecommerce.ecommerce.services.categoryServices;
import com.bharath.ecommerce.ecommerce.services.productServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private productServices productServices;

    @Autowired
    private categoryServices categoryServices;

    @GetMapping("/get_all_users")
    public ResponseEntity<Object> getAllUsers(){
        List<LocalUser> users = userServices.returnUsers();
        if(users==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PostMapping("/add_product")
    public ResponseEntity<Object> addNewProduct(
            @Valid @ModelAttribute ProductDTO newProduct,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            Product product = productServices.addNewProduct(newProduct, imageFile);
            if (product == null) {
                return new ResponseEntity<>("Product Already Exists", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Image Upload Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_all_Products")
    public ResponseEntity<Object> getAllProducts(){
        List<Product> allProducts = productServices.getAllProducts();
        if (allProducts==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @PostMapping("/add_new_admin")
    public ResponseEntity<Object> addNewAdmin(@Valid @RequestBody LocalUser newAdmin){
        LocalUser admin = userServices.addNewAdmin(newAdmin);
        if (admin==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add_new_category")
    public ResponseEntity<Object> addNewCategory(
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        try {
            Category category = categoryServices.addNewCategory(categoryName, imageFile);
            if (category == null) {
                return new ResponseEntity<>("Category Already Exists", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Image Upload Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_product_byId/{product_id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable long product_id){
        productServices.DeleteProductById(product_id);
        return new ResponseEntity<>(HttpStatus.OK);
  }
//    @DeleteMapping("/delete_product_byName/{product_name}")
//    public ResponseEntity<Object> deleteProductByName(@PathVariable String product_name){
//        productServices.DeleteProductByName(product_name);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

  @DeleteMapping("/delete_category_byName/{category_name}")
    public ResponseEntity<Object> deleteCategoryByName(@PathVariable String category_name){
        categoryServices.deleteCategoryByName(category_name);
        return new ResponseEntity<>(HttpStatus.OK);
  }

}
