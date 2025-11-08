package com.innomax.InnomaxBackend.riskProfile.dto;

import com.innomax.InnomaxBackend.riskProfile.entity.RiskType;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RiskProfileSummary {
    private Long resultId;
    private Long userId;
    private String fullName;
    private String email;
    private Integer score;
    private RiskType riskType;
    private LocalDateTime createdAt;
}
