package com.bharath.ecommerce.ecommerce.services;

import com.bharath.ecommerce.ecommerce.DAO.CategoryJpaRepository;
import com.bharath.ecommerce.ecommerce.DAO.ProductJpaRepository;
import com.bharath.ecommerce.ecommerce.entities.Category;
import com.bharath.ecommerce.ecommerce.entities.DTOs.ProductDTO;
import com.bharath.ecommerce.ecommerce.entities.Inventory;
import com.bharath.ecommerce.ecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class productServices {

    @Autowired
    private ProductJpaRepository p_repository;

    @Autowired
    private CategoryJpaRepository c_repository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Value("${default.category.image}")
    private String defaultCategoryImage;

//    @Value("${image.upload.dir}")
//    private String uploadDir;

    @Transactional
    public Product addNewProduct(ProductDTO new_product, MultipartFile imageFile) throws IOException {
        if (p_repository.existsByProductNameIgnoreCase(new_product.getProductName())) {
            return null;
        } else {
            Category category = c_repository.findByCategoryNameIgnoreCase(new_product.getCategoryName());
            if (category == null) {
                category = new Category();
                category.setCategoryName(new_product.getCategoryName());
                category.setImageUrl(defaultCategoryImage);
                category = c_repository.save(category);
            }

            Inventory inventory = new Inventory();
            inventory.setQuantity(new_product.getInventoryCount());

            String productImageUrl = imageUploadService.uploadImage(imageFile);

            Product prod_build = Product.builder()
                    .productName(new_product.getProductName())
                    .price(new_product.getPrice())
                    .inventory(inventory)
                    .shortDescription(new_product.getShortDescription())
                    .longDescription(new_product.getLongDescription())
                    .category(category)
                    .imageUrl(productImageUrl)
                    .build();

            inventory.setProduct(prod_build);

            return p_repository.save(prod_build);
        }
    }


    public List<Product> getAllProducts(){
        List<Product> all = p_repository.findAll();
        if(all.isEmpty()){
            return null;
        }else{
            return all;
        }
    }



    public void DeleteProductById(long id) {
        Optional<Product> optionalProduct = p_repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Delete the product image file
            String imageName = product.getImageUrl();
            if (imageName != null && !imageName.isEmpty() && !imageName.equals("uploads/default-category-image.png")) {
//                Path imagePath = Paths.get(uploadDir, imageName);<- if only image name is stored in the database
//                System.out.println(imagePath);
//                String imageName = Paths.get(imageUrl).getFileName().toString(); <-in case you need image name
                try {
                    Files.deleteIfExists(Path.of(imageName));
                } catch (IOException e) {
                    // Handle the exception
                    e.printStackTrace();
                }
            }

            // Delete the product from the database
            p_repository.deleteById(id);
        }
    }
//    public void DeleteProductByName(String name){
//        p_repository.deleteByProductNameIgnoreCase(name);
//    }

    public List<Product> getRandomProducts(int count) {
        try {
            Pageable pageable = PageRequest.of(0, count);
            return p_repository.findRandomProducts(pageable);
        } catch (Exception e) {
            // Log the exception (optional)
            // e.g., log.error("Failed to retrieve random products", e);
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }
}
