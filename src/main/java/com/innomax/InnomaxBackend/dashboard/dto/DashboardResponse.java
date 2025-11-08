package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private String name;
    private String email;
    private double totalAssets;
    private double totalLiabilities;
    private double netWorth;
    private List<Map<String, Object>> goals;
    private Map<String, String> riskProfile;
}
