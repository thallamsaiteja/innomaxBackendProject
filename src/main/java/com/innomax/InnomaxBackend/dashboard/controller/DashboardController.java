package com.innomax.InnomaxBackend.dashboard.controller;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.dashboard.dto.*;
import com.innomax.InnomaxBackend.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/client")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ==================== CLIENT DASHBOARD ====================
    @GetMapping("/client")
    public DashboardResponse getClientDashboard() {
        // Dummy test user for now (you can replace with logged-in user later)
        User testUser = new User();
        testUser.setFullName("Sai Teja");
        testUser.setEmail("sai@gmail.com");
        return dashboardService.getClientDashboard(testUser);
    }

    // ==================== ADMIN DASHBOARD ====================

    // Get all clients
    @GetMapping("/admin/clients")
    public List<User> getAllClients() {
        return dashboardService.getAllClients();
    }

    // Get single client by ID
    @GetMapping("/admin/client/{id}")
    public User getClientById(@PathVariable Long id) {
        return dashboardService.getClientById(id);
    }

    // Admin Overview (summary numbers)
    @GetMapping("/admin/overview")
    public DashboardStats getAdminOverview() {
        return dashboardService.getAdminOverview();
    }

    // ==================== CHARTS ====================

    // Bar chart: SIP progress for last N months (default 6)
    @GetMapping("/admin/charts/sip-progress")
    public List<SipProgressPoint> sipProgress(@RequestParam(defaultValue = "6") int months) {
        return dashboardService.getSipProgress(months);
    }

    // Pie chart: goal categories
    @GetMapping("/admin/charts/goal-distribution")
    public List<CategoryCount> goalDistribution() {
        return dashboardService.getGoalDistribution();
    }

    // Line chart: new clients per month (default 6)
    @GetMapping("/admin/charts/customer-growth")
    public List<TimePoint> customerGrowth(@RequestParam(defaultValue = "6") int months) {
        return dashboardService.getCustomerGrowth(months);
    }

    // Alerts bundle (KYC pending, missed SIPs, expiring risk profiles)
    @GetMapping("/admin/alerts")
    public AlertsResponse alerts() {
        return dashboardService.getAlerts();
    }
}
