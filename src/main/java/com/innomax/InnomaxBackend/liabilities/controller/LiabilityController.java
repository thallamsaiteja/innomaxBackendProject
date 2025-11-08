package com.innomax.InnomaxBackend.liabilities.controller;

import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.liabilities.service.LiabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/liabilities")
@CrossOrigin(origins = "http://localhost:3000")
public class LiabilityController {

    @Autowired
    private LiabilityService liabilityService;

    @PostMapping("/{userId}")
    public Liability addLiability(@PathVariable Long userId, @RequestBody Liability liability) {
        return liabilityService.addLiability(userId, liability);
    }

    @GetMapping("/{userId}")
    public List<Liability> getLiabilities(@PathVariable Long userId) {
        return liabilityService.getLiabilitiesByUser(userId);
    }

    @DeleteMapping("/{userId}/{liabilityId}")
    public String deleteLiability(@PathVariable Long userId, @PathVariable Long liabilityId) {
        liabilityService.deleteLiability(userId, liabilityId);
        return "Liability deleted successfully!";
    }
}
