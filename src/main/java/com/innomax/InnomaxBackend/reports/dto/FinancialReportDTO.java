package com.innomax.InnomaxBackend.reports.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReportDTO {
    private double totalGoals;
    private double totalLiabilities;
    private double netWorth;
    private String riskType;
}
