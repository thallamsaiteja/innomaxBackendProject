package com.innomax.InnomaxBackend.riskProfile.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class RiskProfileRequest {
    /** 12 answers, each 0–2 */
    @NotNull
    @Size(min = 12, max = 12, message = "Exactly 12 answers are required")
    private List<Integer> answers;
}
