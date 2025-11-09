// src/main/java/com/innomax/InnomaxBackend/dashboard/controller/AdminDataController.java
package com.innomax.InnomaxBackend.dashboard.controller;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.repository.GoalRepository;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import com.innomax.InnomaxBackend.riskProfile.repository.RiskProfileRepository;
import com.innomax.InnomaxBackend.sips.entity.SIP;
import com.innomax.InnomaxBackend.sips.repository.SIPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard/admin")
@RequiredArgsConstructor
public class AdminDataController {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final RiskProfileRepository riskProfileRepository;
    private final SIPRepository sipRepository;

    // Clients list (only CLIENT role)
    @GetMapping("/clients")
    public List<User> getClients() {
        return userRepository.findByRoleIgnoreCase("CLIENT");
    }

    @GetMapping("/goals")
    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }

    @GetMapping("/risks")
    public List<RiskProfileResult> getRisks() {
        return riskProfileRepository.findAll();
    }

    @GetMapping("/sips")
    public List<SIP> getSips() {
        return sipRepository.findAll();
    }
}
