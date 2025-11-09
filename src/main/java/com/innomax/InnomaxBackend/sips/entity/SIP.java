package com.innomax.InnomaxBackend.sips.entity;

import com.innomax.InnomaxBackend.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "client_sips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SIP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "monthly_amount", nullable = false)
    private double monthlyAmount;

    @Column(name = "duration_months", nullable = false)
    private int durationMonths;

    @Column(name = "returns_expected")
    private Double returnsExpected;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "next_due_date")
    private LocalDate nextDueDate;

    @Column(name = "total_invested")
    private Double totalInvested;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
