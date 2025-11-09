package com.innomax.InnomaxBackend.auth.repository;
import java.util.List;

import com.innomax.InnomaxBackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);

    long countByRoleIgnoreCase(String role);
    List<User> findByRoleIgnoreCase(String role);
    @Query("SELECT COUNT(u) FROM User u WHERE UPPER(u.role) = 'CLIENT'")
    long countClients();

    // ✅ Case-insensitive find clients
    @Query("SELECT u FROM User u WHERE UPPER(u.role) = 'CLIENT'")
    List<User> findAllClients();

    List<User> findByRole(String role);

    // 🔹 New: Search by name, email, pan, or mobile
    @Query("""
           SELECT u FROM User u 
           WHERE LOWER(u.fullName) LIKE %:query% 
              OR LOWER(u.email) LIKE %:query%
              OR LOWER(u.mobile) LIKE %:query%
           """)
    List<User> searchClients(String query);
}
