package com.innomax.InnomaxBackend.riskProfile.repository;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // ✅ import added
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiskProfileRepository extends JpaRepository<RiskProfileResult, Long> {

    Optional<RiskProfileResult> findByUser(User user);

    void deleteByUser(User user);

    @Query("select count(distinct r.user.userId) from RiskProfileResult r")
    long countCompleted();
}
