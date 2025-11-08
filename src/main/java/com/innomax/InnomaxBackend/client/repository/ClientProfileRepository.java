package com.innomax.InnomaxBackend.client.repository;

import com.innomax.InnomaxBackend.client.entity.ClientProfile;
import com.innomax.InnomaxBackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientProfileRepository extends JpaRepository<ClientProfile, Long> {
    Optional<ClientProfile> findByUser(User user);
}
