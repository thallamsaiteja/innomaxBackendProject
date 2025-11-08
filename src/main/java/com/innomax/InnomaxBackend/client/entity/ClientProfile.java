package com.innomax.InnomaxBackend.client.entity;

import com.innomax.InnomaxBackend.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "client_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;

    private String company;
    private String designation;
    private String department;
    private Integer yearsOfExperience;
    private Double annualSalary;
    private String occupationType;
    private String profileImageUrl;

    @Column(nullable = false)
    private String pan;
}
