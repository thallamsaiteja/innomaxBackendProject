package com.innomax.InnomaxBackend.sips.repository;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.sips.entity.SIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // ✅ import added
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SIPRepository extends JpaRepository<SIP, Long> {

    List<SIP> findByUser(User user);

    @Query("select count(distinct s.user.userId) from SIP s")
    long countUsersWithSips();

    @Query("select count(s) from SIP s where s.isActive = false")
    long countCompletedSips();
}
