package com.innomax.InnomaxBackend.dashboard.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.dashboard.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    // ==================== CLIENT DASHBOARD ====================
    public DashboardResponse getClientDashboard(User user) {
        double totalAssets = 1870000;
        double totalLiabilities = 1140000;
        double netWorth = totalAssets - totalLiabilities;

        List<Map<String, Object>> goals = List.of(
                Map.of("name", "Buy House", "target", 2500000, "achieved", 800000, "progress", 32),
                Map.of("name", "Child Education", "target", 1000000, "achieved", 300000, "progress", 30)
        );

        Map<String, String> riskProfile = Map.of(
                "riskType", "Moderate",
                "recommendedPortfolio", "60% Equity / 40% Debt"
        );

        return new DashboardResponse(
                user.getFullName(),
                user.getEmail(),
                totalAssets,
                totalLiabilities,
                netWorth,
                goals,
                riskProfile
        );
    }

    // ==================== ADMIN DASHBOARD ====================
    public List<User> getAllClients() {
        return userRepository.findAll().stream()
                .filter(u -> "CLIENT".equalsIgnoreCase(u.getRole()))
                .toList();
    }

    public User getClientById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public DashboardStats getAdminOverview() {
        long totalClients = userRepository.findAll().stream()
                .filter(u -> "CLIENT".equalsIgnoreCase(u.getRole()))
                .count();

        long riskProfilesCompleted = totalClients / 2;
        long activeSIPs = 8;
        double totalSIPAmount = 450000;
        String topGoalCategory = "Buy House";

        return DashboardStats.builder()
                .totalClients(totalClients)
                .riskProfilesCompleted(riskProfilesCompleted)
                .activeSIPs(activeSIPs)
                .totalSIPAmount(totalSIPAmount)
                .topGoalCategory(topGoalCategory)
                .build();
    }

    // ==================== CHART LOGIC ====================
    public List<SipProgressPoint> getSipProgress(int monthsBack) {
        return List.of(
                new SipProgressPoint("2025-07", 30000, 25000),
                new SipProgressPoint("2025-08", 35000, 30000),
                new SipProgressPoint("2025-09", 40000, 38000),
                new SipProgressPoint("2025-10", 42000, 40000),
                new SipProgressPoint("2025-11", 45000, 43000)
        );
    }

    public List<CategoryCount> getGoalDistribution() {
        return List.of(
                new CategoryCount("Buy House", 5),
                new CategoryCount("Child Education", 3),
                new CategoryCount("Retirement", 2)
        );
    }

    public List<TimePoint> getCustomerGrowth(int monthsBack) {
        return List.of(
                new TimePoint("2025-07", 2),
                new TimePoint("2025-08", 3),
                new TimePoint("2025-09", 5),
                new TimePoint("2025-10", 4),
                new TimePoint("2025-11", 6)
        );
    }

    public AlertsResponse getAlerts() {
        List<Map<String, Object>> incompleteKyc = List.of(
                Map.of("userId", 1, "name", "John Doe", "note", "KYC pending"),
                Map.of("userId", 2, "name", "Jane Smith", "note", "KYC pending")
        );

        List<Map<String, Object>> missedSips = List.of(
                Map.of("sipId", 101, "userId", 1, "note", "SIP overdue"),
                Map.of("sipId", 102, "userId", 2, "note", "SIP delayed")
        );

        List<Map<String, Object>> expiringRisk = List.of(
                Map.of("userId", 3, "name", "Client X", "note", "Risk profile needs refresh")
        );

        return AlertsResponse.builder()
                .incompleteKyc(incompleteKyc)
                .missedSips(missedSips)
                .expiringRiskProfiles(expiringRisk)
                .build();
    }
}
