package com.bharath.ecommerce.ecommerce.DAO;

import com.bharath.ecommerce.ecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    boolean existsByProductNameIgnoreCase(String productName);









    @Query(value = "SELECT p FROM Product p ORDER BY FUNCTION('RAND')")
    List<Product> findRandomProducts(Pageable pageable);

}
