package com.innomax.InnomaxBackend.sips.controller;

import com.innomax.InnomaxBackend.sips.entity.SIP;
import com.innomax.InnomaxBackend.sips.service.SIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/sips")
@CrossOrigin(origins = "http://localhost:3000")
public class SIPController {

    @Autowired
    private SIPService sipService;

    @PostMapping("/{userId}")
    public SIP addSIP(@PathVariable Long userId, @RequestBody SIP sip) {
        return sipService.createSIP(userId, sip);
    }

    @GetMapping("/{userId}")
    public List<SIP> getSIPs(@PathVariable Long userId) {
        return sipService.getSIPsByUser(userId);
    }

    @DeleteMapping("/{userId}/{sipId}")
    public String deleteSIP(@PathVariable Long userId, @PathVariable Long sipId) {
        sipService.deleteSIP(sipId);
        return "SIP deleted successfully!";
    }
}
