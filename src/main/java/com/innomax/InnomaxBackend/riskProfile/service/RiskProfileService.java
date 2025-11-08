package com.innomax.InnomaxBackend.riskProfile.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskProfileResult;
import com.innomax.InnomaxBackend.riskProfile.entity.RiskType;
import com.innomax.InnomaxBackend.riskProfile.repository.RiskProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RiskProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiskProfileRepository riskProfileRepository;

    // 🔹 Submit new quiz responses
    @Transactional
    public RiskProfileResult submitQuiz(Long userId, List<Integer> answers) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Remove old result if user is retaking
        riskProfileRepository.deleteByUser(user);

        // Each question carries 2 marks, so sum total
        int totalScore = answers.stream().mapToInt(Integer::intValue).sum();

        RiskType type;
        if (totalScore <= 10) {
            type = RiskType.CONSERVATIVE;
        } else if (totalScore <= 20) {
            type = RiskType.MODERATE;
        } else {
            type = RiskType.AGGRESSIVE;
        }

        RiskProfileResult result = RiskProfileResult.builder()
                .user(user)
                .score(totalScore)
                .riskType(type)
                .createdAt(LocalDateTime.now())
                .build();

        return riskProfileRepository.save(result);
    }

    // 📊 Fetch user’s last quiz result
    public RiskProfileResult getResult(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return riskProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No risk profile found"));
    }

    // 🔁 Retake quiz (delete previous result)
    @Transactional
    public void retakeQuiz(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("🗑 Deleting previous quiz result for user: " + user.getEmail());
        riskProfileRepository.deleteByUser(user);
    }
}
