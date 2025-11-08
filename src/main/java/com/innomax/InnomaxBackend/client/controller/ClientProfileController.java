package com.innomax.InnomaxBackend.client.controller;

import com.innomax.InnomaxBackend.client.entity.ClientProfile;
import com.innomax.InnomaxBackend.client.service.ClientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientProfileController {

    @Autowired
    private ClientProfileService service;

    // --- Get Profile by User ID ---
    @GetMapping("/profile/{userId}")
    public ClientProfile getProfile(@PathVariable Long userId) {
        return service.getProfile(userId);
    }

    // --- Save or Update Profile ---
    @PostMapping("/profile/{userId}")
    public ClientProfile saveProfile(@PathVariable Long userId, @RequestBody ClientProfile profile) {
        return service.saveProfile(userId, profile);
    }
}
