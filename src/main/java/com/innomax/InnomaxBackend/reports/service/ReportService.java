package com.innomax.InnomaxBackend.reports.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.repository.GoalRepository;
import com.innomax.InnomaxBackend.liabilities.entity.Liability;
import com.innomax.InnomaxBackend.liabilities.repository.LiabilityRepository;
import com.innomax.InnomaxBackend.reports.dto.FinancialReportDTO;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import com.innomax.InnomaxBackend.riskProfile.repository.RiskProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private LiabilityRepository liabilityRepository;

    @Autowired
    private RiskProfileRepository riskProfileRepository;

    /**
     * Generates a Financial Summary for the user — including Goals, Liabilities, Net Worth, and Risk Type.
     */
    public FinancialReportDTO generateReport(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // --- Goals ---
        double totalGoals = goalRepository.findByUser(user)
                .stream()
                .mapToDouble(Goal::getTargetAmount)
                .sum();

        // --- Liabilities ---
        double totalLiabilities = liabilityRepository.findByUser(user)
                .stream()
                .mapToDouble(Liability::getAmount) // ✅ make sure your Liability entity has "amount" field
                .sum();

        // --- Net Worth ---
        double netWorth = totalGoals - totalLiabilities;

        // --- Risk Profile ---
        RiskProfileResult riskProfile = riskProfileRepository.findByUser(user).orElse(null);
        String riskType = (riskProfile != null && riskProfile.getRiskType() != null)
                ? riskProfile.getRiskType().name()
                : "N/A";

        // --- Final DTO ---
        return FinancialReportDTO.builder()
                .totalGoals(totalGoals)
                .totalLiabilities(totalLiabilities)
                .netWorth(netWorth)
                .riskType(riskType)
                .build();
    }
}
