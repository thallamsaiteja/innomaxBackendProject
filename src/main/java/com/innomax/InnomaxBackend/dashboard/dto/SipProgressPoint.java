package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SipProgressPoint {
    private String month;
    private double planned;
    private double completed;
}
