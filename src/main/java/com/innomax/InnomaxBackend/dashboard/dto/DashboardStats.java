package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStats {
    private long totalClients;
    private long riskProfilesCompleted;
    private long activeSIPs;
    private double totalSIPAmount;
    private String topGoalCategory;
}
