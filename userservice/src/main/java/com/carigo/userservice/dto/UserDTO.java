package com.carigo.userservice.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String fullName;

    private String email;

    private String mobileNumber;

    private String role;

    private String kycStatus;

    private String drivingLicenseNumber;
    private String aadharNumber;

    private String addressLine;
    private String city;
    private String state;
    private String pincode;

    private Double averageRating = 0.0;

    private byte[] image;

}
