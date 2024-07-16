package com.bharath.ecommerce.ecommerce.services;

import com.bharath.ecommerce.ecommerce.DAO.CategoryJpaRepository;
import com.bharath.ecommerce.ecommerce.entities.Category;
import com.bharath.ecommerce.ecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class categoryServices {

    @Autowired
    private CategoryJpaRepository cat_repository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Value("${default.category.image}")
    private String defaultCategoryImage;

    @Transactional
    public Category addNewCategory(String categoryName, MultipartFile imageFile) throws IOException {
        if (cat_repository.existsByCategoryNameIgnoreCase(categoryName)) {
            return null;
        }
        String imageUrl;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = imageUploadService.uploadImage(imageFile);
        } else {
            imageUrl = defaultCategoryImage;
        }

        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setImageUrl(imageUrl);

        return cat_repository.save(category);
    }
    public void DeleteCategoryById(long id) {
        Optional<Category> optionalCategory = cat_repository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            // Delete the category image file
            String imageUrl = category.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty() && !imageUrl.equals("uploads/default-category-image.png")) {
                try {
                    Files.deleteIfExists(Path.of(imageUrl));
                } catch (IOException e) {
                    // Handle the exception
                    e.printStackTrace();
                }
            }

            // Delete the category from the database
            cat_repository.deleteById(id);
        }
    }

    public void deleteCategoryByName(String cat_name){
        cat_repository.deleteByCategoryNameIgnoreCase(cat_name);
    }

    public List<Product> getProductsByCategoryName(String cat_name){
        Category category = cat_repository.findByCategoryNameIgnoreCase(cat_name);
        List<Product> catProducts = category.getCatProducts();
        if(catProducts.isEmpty()){
            return null;
        }
        else return catProducts;
    }


}
