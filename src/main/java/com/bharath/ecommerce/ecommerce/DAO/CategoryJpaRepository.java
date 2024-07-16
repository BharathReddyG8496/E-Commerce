package com.bharath.ecommerce.ecommerce.DAO;

import com.bharath.ecommerce.ecommerce.entities.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);

    void deleteByCategoryNameIgnoreCase(String categoryName);

    Category findByCategoryNameIgnoreCase(String categoryName);
}
