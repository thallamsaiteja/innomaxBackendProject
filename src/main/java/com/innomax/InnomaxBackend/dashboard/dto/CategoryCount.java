package com.innomax.InnomaxBackend.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCount {
    private String category;
    private long count;
}
