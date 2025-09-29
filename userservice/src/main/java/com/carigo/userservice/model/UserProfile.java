package com.carigo.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.carigo.userservice.helper.KycStatus;
import com.carigo.userservice.helper.UserRole;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus = KycStatus.PENDING;

    private String drivingLicenseNumber;
    private String aadharNumber;

    private String addressLine;
    private String city;
    private String state;
    private String pincode;

    private Double averageRating = 0.0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Lob
    private byte[] image;
}
