package com.innomax.InnomaxBackend.client.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.client.entity.ClientProfile;
import com.innomax.InnomaxBackend.client.repository.ClientProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientProfileRepository profileRepo;

    // --- Fetch Profile ---
    public ClientProfile getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return profileRepo.findByUser(user).orElse(new ClientProfile());
    }

    // --- Save or Update Profile ---
    public ClientProfile saveProfile(Long userId, ClientProfile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        return profileRepo.save(profile);
    }
}
