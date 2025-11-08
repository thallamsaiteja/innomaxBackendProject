package com.innomax.InnomaxBackend.liabilities.repository;

import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LiabilityRepository extends JpaRepository<Liability, Long> {
    List<Liability> findByUser(User user);
    Liability findByIdAndUser(Long id, User user);
}
