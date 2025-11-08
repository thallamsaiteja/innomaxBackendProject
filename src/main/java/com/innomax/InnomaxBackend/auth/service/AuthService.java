package com.innomax.InnomaxBackend.auth.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.entity.PasswordResetToken;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.auth.repository.PasswordResetRepository;
import com.innomax.InnomaxBackend.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ===================== REGISTER =====================
    public User registerUser(User user) {

        // 1. Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use.");
        }

        // 2. Check if mobile already exists
        if (userRepository.findByMobile(user.getMobile()).isPresent()) {
            throw new RuntimeException("Mobile number already registered.");
        }

        // Proceed with registration if no duplicates found
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // ===================== LOGIN =====================
    public String login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(user);
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // ===================== FORGOT PASSWORD =====================
    public String forgotPassword(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setEmail(email);
        resetToken.setToken(token);
        resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 10 * 60 * 1000)); // 10 minutes

        passwordResetRepository.save(resetToken);

        // Return token directly for testing (no email)
        return token;
    }

    // ===================== RESET PASSWORD =====================
    public String resetPassword(String token, String newPassword) {
        var resetToken = passwordResetRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiryDate().before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        var user = userRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordResetRepository.delete(resetToken); // cleanup used token
        return "Password updated successfully!";
    }
}
