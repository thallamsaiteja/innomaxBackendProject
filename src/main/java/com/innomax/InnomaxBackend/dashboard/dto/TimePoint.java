package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimePoint {
    private String month;
    private long value;
}
