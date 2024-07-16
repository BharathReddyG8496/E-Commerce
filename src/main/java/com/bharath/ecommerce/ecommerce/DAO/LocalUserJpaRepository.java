package com.bharath.ecommerce.ecommerce.DAO;

import com.bharath.ecommerce.ecommerce.entities.LocalUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LocalUserJpaRepository extends JpaRepository<LocalUser,Long> {
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);

    LocalUser findByUsername(String username);

    long deleteByUsername(String username);


}
