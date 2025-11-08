package com.innomax.InnomaxBackend.liabilities.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.liabilities.repository.LiabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiabilityService {

    @Autowired
    private LiabilityRepository liabilityRepository;

    @Autowired
    private UserRepository userRepository;

    public Liability addLiability(Long userId, Liability liability) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        liability.setUser(user);
        return liabilityRepository.save(liability);
    }

    public List<Liability> getLiabilitiesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return liabilityRepository.findByUser(user);
    }

    public void deleteLiability(Long userId, Long liabilityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Liability liability = liabilityRepository.findByIdAndUser(liabilityId, user);
        if (liability == null) throw new RuntimeException("Liability not found");
        liabilityRepository.delete(liability);
    }
}
