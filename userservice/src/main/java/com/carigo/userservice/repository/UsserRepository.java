package com.carigo.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carigo.userservice.model.UserProfile;

@Repository
public interface UsserRepository extends JpaRepository<UserProfile, String> {

    Boolean existsByid(String id);

    boolean existsByFullName(String name);

    boolean existsByMobileNumber(String mobile);

    boolean existsByDrivingLicenseNumber(String driving);

    boolean existsByAddressLine(String address);

    boolean existsByCity(String city);

    boolean existsByState(String state);

    boolean existsByPincode(String pincode);

    boolean existsByAadharNumber(String aadhar);

    Boolean existsByEmail(String email);

    Optional<UserProfile> findByFullName(String fullName);

    Optional<UserProfile> findByMobileNumber(String mobileNumber);

    Optional<UserProfile> findByDrivingLicenseNumber(String drivingLicenseNumber);

    Optional<UserProfile> findByAadharNumber(String aadharNumber);

    Optional<UserProfile> findByAddressLine(String addressLine);

    Optional<UserProfile> findByCity(String city);

    Optional<UserProfile> findByState(String state);

    Optional<UserProfile> findByPincode(String pincode);

    Optional<UserProfile> findByEmail(String email);

}
