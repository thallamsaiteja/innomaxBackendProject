package com.innomax.InnomaxBackend.dashboard.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.repository.GoalRepository;
import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.liabilities.repository.LiabilityRepository;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import com.innomax.InnomaxBackend.riskProfile.repository.RiskProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final LiabilityRepository liabilityRepository;
    private final RiskProfileRepository riskProfileRepository;

    // 1️⃣ Total Clients
    public long getTotalClients() {
        return userRepository.findAll()
                .stream()
                .filter(u -> "CLIENT".equalsIgnoreCase(u.getRole()))
                .count();
    }

    // 2️⃣ Goals + Liabilities Summary
    public Map<String, Object> getGoalsAndLiabilitiesSummary() {
        double totalGoals = goalRepository.findAll().stream()
                .mapToDouble(Goal::getTargetAmount).sum();
        double totalLiabilities = liabilityRepository.findAll().stream()
                .mapToDouble(Liability::getAmount).sum();

        Map<String, Object> map = new HashMap<>();
        map.put("totalGoalsValue", totalGoals);
        map.put("totalLiabilitiesValue", totalLiabilities);
        map.put("netWorth", totalGoals - totalLiabilities);
        return map;
    }

    // 3️⃣ Top Goal Categories
    public Map<String, Long> getTopGoalCategories() {
        return goalRepository.findAll().stream()
                .collect(Collectors.groupingBy(Goal::getGoalName, Collectors.counting()));
    }

    // 4️⃣ Risk Profile Distribution
    public Map<String, Long> getRiskProfileStats() {
        return riskProfileRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRiskType().name(),
                        Collectors.counting()
                ));
    }

    // 5️⃣ Combined Dashboard Summary
    public Map<String, Object> getDashboardOverview() {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalClients", getTotalClients());
        summary.put("goalsAndLiabilities", getGoalsAndLiabilitiesSummary());
        summary.put("topGoalCategories", getTopGoalCategories());
        summary.put("riskProfileStats", getRiskProfileStats());
        return summary;
    }
}
