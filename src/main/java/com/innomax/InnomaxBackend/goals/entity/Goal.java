package com.innomax.InnomaxBackend.goals.entity;

import com.innomax.InnomaxBackend.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "client_goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to user (so we know who owns the goal)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(nullable = false)
    private String goalName;  // Example: "Buy a House"

    // ✅ NEW FIELD — helps for analytics (category)
    // Example: "Education", "Retirement", "Wealth Creation"
    @Column(nullable = true)
    private String category;

    @Column(nullable = false)
    private double targetAmount;

    @Column(nullable = false)
    private double achievedAmount;

    private String timeline; // Example: "5 years"

    private double progress; // Computed percentage

    @PrePersist
    @PreUpdate
    private void calculateProgress() {
        if (targetAmount > 0) {
            this.progress = (achievedAmount / targetAmount) * 100;
        } else {
            this.progress = 0;
        }
    }
}
