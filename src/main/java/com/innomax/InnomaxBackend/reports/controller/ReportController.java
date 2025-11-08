package com.innomax.InnomaxBackend.reports.controller;

import com.innomax.InnomaxBackend.reports.dto.FinancialReportDTO;
import com.innomax.InnomaxBackend.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 📊 Generate client report
    @GetMapping("/{userId}")
    public FinancialReportDTO generateReport(@PathVariable Long userId) {
        return reportService.generateReport(userId);
    }
}
