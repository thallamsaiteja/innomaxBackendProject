package com.innomax.InnomaxBackend.liabilities.entity;

import com.innomax.InnomaxBackend.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_liabilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Liability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // link to the user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String liabilityType; // e.g., "Home Loan", "Car Loan", "Credit Card"

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double remainingAmount;

    private double monthlyEMI;

    private String lender; // bank or source

    private String dueDate; // e.g., "5th of every month"


}
