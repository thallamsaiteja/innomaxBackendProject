package com.innomax.InnomaxBackend.riskProfile.controller;

import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import com.innomax.InnomaxBackend.riskProfile.service.RiskProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/risk-profile")
@CrossOrigin(origins = "http://localhost:3000")
public class RiskProfileController {

    @Autowired
    private RiskProfileService riskProfileService;

    // ✅ Submit quiz
    @PostMapping("/{userId}")
    public ResponseEntity<RiskProfileResult> submitQuiz(
            @PathVariable Long userId,
            @RequestBody List<Integer> answers) {
        RiskProfileResult result = riskProfileService.submitQuiz(userId, answers);
        return ResponseEntity.ok(result);
    }

    // ✅ Fetch latest quiz
    @GetMapping("/{userId}")
    public ResponseEntity<RiskProfileResult> getResult(@PathVariable Long userId) {
        RiskProfileResult result = riskProfileService.getResult(userId);
        return ResponseEntity.ok(result);
    }

    // ✅ Retake quiz (delete old result)
    @DeleteMapping("/{userId}/retake")
    public ResponseEntity<String> retakeQuiz(@PathVariable Long userId) {
        riskProfileService.retakeQuiz(userId);
        return ResponseEntity.ok("Previous quiz result deleted. You can retake the assessment.");
    }
}
