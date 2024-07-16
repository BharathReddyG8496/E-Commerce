package com.bharath.ecommerce.ecommerce.DAO;

import com.bharath.ecommerce.ecommerce.entities.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AddressJpaRepository extends JpaRepository<Address,Long> {
}
