package com.innomax.InnomaxBackend.dashboard.service;

import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.repository.GoalRepository;
import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.liabilities.repository.LiabilityRepository;
import com.innomax.InnomaxBackend.riskProfile.repository.RiskProfileRepository;
import com.innomax.InnomaxBackend.sips.repository.SIPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    public final UserRepository userRepository;
    public final GoalRepository goalRepository;
    public final LiabilityRepository liabilityRepository;
    public final RiskProfileRepository riskProfileRepository;
    public final SIPRepository sipRepository;

    // 🧩 (A) Core Stats
    private long getTotalClients() {
        return userRepository.countClients();
    }

    private long getRiskProfilesCompleted() {
        return riskProfileRepository.countCompleted();
    }

    private long getActiveSips() {
        long total = sipRepository.count();
        long completed = sipRepository.countCompletedSips();
        return Math.max(0, total - completed);
    }

    // 🧩 (B) SIP progress: Active vs Completed
    public Map<String, Long> getSipProgress() {
        Map<String, Long> map = new LinkedHashMap<>();
        map.put("Active SIPs", sipRepository.count() - sipRepository.countCompletedSips());
        map.put("Completed SIPs", sipRepository.countCompletedSips());
        return map;
    }

    // 🧩 (C) Goal distribution by category
    public Map<String, Long> getGoalDistribution() {
        return goalRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        g -> g.getCategory() == null ? "Uncategorized" : g.getCategory(),
                        Collectors.counting()
                ));
    }

    // 🧩 (D) Customer growth by month
    public Map<String, Long> getCustomerGrowth() {
        return userRepository.findAll().stream()
                .filter(u -> "CLIENT".equalsIgnoreCase(u.getRole()) && u.getCreatedAt() != null)
                .collect(Collectors.groupingBy(
                        u -> u.getCreatedAt()
                                .getMonth()
                                .toString()
                                .substring(0, 3), // JAN, FEB, MAR
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
    }


    // 🧩 (E) Goals & Liabilities
    public Map<String, Object> getGoalsAndLiabilitiesSummary() {
        double totalGoals = goalRepository.findAll().stream()
                .mapToDouble(Goal::getTargetAmount).sum();
        double totalLiabilities = liabilityRepository.findAll().stream()
                .mapToDouble(Liability::getAmount).sum();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("totalGoalsValue", totalGoals);
        map.put("totalLiabilitiesValue", totalLiabilities);
        map.put("netWorth", totalGoals - totalLiabilities);
        return map;
    }

    // 🧩 (F) Risk Profile Distribution
    public Map<String, Long> getRiskProfileStats() {
        return riskProfileRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRiskType().name(),
                        Collectors.counting()
                ));
    }

    // ✅ (G) Unified Dashboard Overview Payload
    public Map<String, Object> getDashboardOverview() {
        Map<String, Object> summary = new LinkedHashMap<>();

        // Top-level cards
        summary.put("totalClients", getTotalClients());
        summary.put("riskProfilesCompleted", getRiskProfilesCompleted());
        summary.put("activeSIPs", getActiveSips());

        // Analytics section
        summary.put("sipProgress", getSipProgress());
        summary.put("goalDistribution", getGoalDistribution());
        summary.put("customerGrowth", getCustomerGrowth());
        summary.put("goalsAndLiabilities", getGoalsAndLiabilitiesSummary());
        summary.put("riskProfileStats", getRiskProfileStats());

        return summary;
    }
}
