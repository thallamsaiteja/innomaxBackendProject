package com.innomax.InnomaxBackend.sips.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.sips.entity.SIP;
import com.innomax.InnomaxBackend.sips.repository.SIPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SIPService {

    @Autowired
    private SIPRepository sipRepository;

    @Autowired
    private UserRepository userRepository;

    // ➕ Create SIP
    public SIP createSIP(Long userId, SIP sipRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        double monthlyAmount = sipRequest.getMonthlyAmount();
        int durationMonths = sipRequest.getDurationMonths();
        double totalInvested = monthlyAmount * durationMonths;

        // If no returnsExpected passed, assume default 10%
        Double returnsExpected = (sipRequest.getReturnsExpected() != null)
                ? sipRequest.getReturnsExpected()
                : 10.0;

        LocalDate startDate = sipRequest.getStartDate();
        LocalDate nextDueDate = startDate.plusMonths(1);

        SIP sip = SIP.builder()
                .user(user)
                .planName(sipRequest.getPlanName())
                .monthlyAmount(monthlyAmount)
                .durationMonths(durationMonths)
                .returnsExpected(returnsExpected)
                .startDate(startDate)
                .nextDueDate(nextDueDate)
                .totalInvested(totalInvested)
                .isActive(true)
                .build();

        return sipRepository.save(sip);
    }

    // 📋 Get all SIPs for a user
    public List<SIP> getSIPsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return sipRepository.findByUser(user);
    }

    // 🗑 Delete SIP
    public void deleteSIP(Long sipId) {
        sipRepository.deleteById(sipId);
    }
}
