package com.innomax.InnomaxBackend.riskProfile.dto;

import com.innomax.InnomaxBackend.riskProfile.entity.RiskType;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RiskProfileResponse {
    private Long resultId;
    private Integer score;
    private RiskType riskType;
    private LocalDateTime createdAt;
}
