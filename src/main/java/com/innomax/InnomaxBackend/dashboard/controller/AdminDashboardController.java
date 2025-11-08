package com.innomax.InnomaxBackend.dashboard.controller;

import com.innomax.InnomaxBackend.dashboard.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard/admin/analytics")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/overview")
    public Map<String, Object> getOverview() {
        return adminDashboardService.getDashboardOverview();
    }

    @GetMapping("/risk-stats")
    public Map<String, Long> getRiskStats() {
        return adminDashboardService.getRiskProfileStats();
    }

    @GetMapping("/goals-liabilities")
    public Map<String, Object> getGoalsAndLiabilities() {
        return adminDashboardService.getGoalsAndLiabilitiesSummary();
    }
}
