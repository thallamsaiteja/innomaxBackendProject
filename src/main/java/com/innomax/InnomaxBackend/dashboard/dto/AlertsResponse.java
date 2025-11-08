package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertsResponse {
    private List<Map<String, Object>> incompleteKyc;
    private List<Map<String, Object>> missedSips;
    private List<Map<String, Object>> expiringRiskProfiles;
}
