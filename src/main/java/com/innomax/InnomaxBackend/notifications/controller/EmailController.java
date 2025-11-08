package com.innomax.InnomaxBackend.notifications.controller;

import com.innomax.InnomaxBackend.notifications.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/notify")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/client")
    public String notifyClient(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message) {

        emailService.sendEmail(to, subject, message);
        return "Email sent successfully to " + to;
    }
}
