
package com.carigo.userservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carigo.userservice.dto.UserDTO;
import com.carigo.userservice.helper.KycStatus;
import com.carigo.userservice.helper.UserRole;
import com.carigo.userservice.model.UserProfile;
import com.carigo.userservice.repository.UsserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UsserRepository usserRepository;

    // ✅ Create User
    public UserProfile createUser(UserDTO userDTO) {
        UserProfile user = new UserProfile();

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());

        // Role default User
        if (userDTO.getRole() != null) {
            user.setRole(UserRole.valueOf(userDTO.getRole().toUpperCase()));
        } else {
            user.setRole(UserRole.User);
        }

        // KYC default PENDING
        if (userDTO.getKycStatus() != null) {
            user.setKycStatus(KycStatus.valueOf(userDTO.getKycStatus().toUpperCase()));
        } else {
            user.setKycStatus(KycStatus.PENDING);
        }

        user.setDrivingLicenseNumber(userDTO.getDrivingLicenseNumber());
        user.setAadharNumber(userDTO.getAadharNumber());
        user.setAddressLine(userDTO.getAddressLine());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setPincode(userDTO.getPincode());
        user.setAverageRating(userDTO.getAverageRating());

        if (userDTO.getImage() != null) {
            user.setImage(userDTO.getImage());
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        UserProfile savedUser = usserRepository.save(user);
        log.info("User Created: {}", savedUser);

        return savedUser;
    }

    // ✅ Update User
    public UserProfile updateProfile(UserDTO userDTO) {
        UserProfile updateUser = new UserProfile();
        updateUser.setFullName(userDTO.getFullName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setMobileNumber(userDTO.getMobileNumber());
        updateUser.setRole(UserRole.User);
        updateUser.setKycStatus(KycStatus.PENDING);
        updateUser.setDrivingLicenseNumber(userDTO.getDrivingLicenseNumber());
        updateUser.setAadharNumber(userDTO.getAadharNumber());
        updateUser.setAddressLine(userDTO.getAddressLine());
        updateUser.setCity(userDTO.getCity());
        updateUser.setState(userDTO.getState());
        updateUser.setPincode(userDTO.getPincode());
        updateUser.setAverageRating(userDTO.getAverageRating());

        if (userDTO.getImage() != null) {
            updateUser.setImage(userDTO.getImage());
        }

        UserProfile saveuser = usserRepository.save(updateUser);
        log.info(saveuser.toString());
        return saveuser;
    }

    public Boolean isPresent(String userid) {
        boolean present = usserRepository.existsById(userid);
        log.info(userid);
        return present;
    }

    public Boolean isEmailpresent(String email) {
        Boolean emailpresent = usserRepository.existsByEmail(email);
        log.info(email);
        return emailpresent;
    }

    public Optional<UserProfile> findUser(String input) {
        if (input.matches("\\d+")) {
            return usserRepository.findById(input)
                    .or(() -> usserRepository.findByAadharNumber(input))
                    .or(() -> usserRepository.findByMobileNumber(input))
                    .or(() -> usserRepository.findByPincode(input));
        }
        if (input.contains("@")) {
            return usserRepository.findByEmail(input);
        }
        return usserRepository.findByFullName(input)
                .or(() -> usserRepository.findByAddressLine(input))
                .or(() -> usserRepository.findByState(input))
                .or(() -> usserRepository.findByDrivingLicenseNumber(input))
                .or(() -> usserRepository.findByCity(input));
    }

    public List<UserProfile> findAllProfiles() {
        return usserRepository.findAll();
    }

    public UserProfile findByUserID(String id) {
        return usserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public boolean isPresentId(String id) {
        return usserRepository.existsById(id);
    }

    public boolean isPresentFullname(String name) {
        return usserRepository.existsByFullName(name);
    }

    public boolean isPresentMobileNumber(String number) {
        return usserRepository.existsByMobileNumber(number);
    }

    public boolean isPresentDriving(String driving) {
        return usserRepository.existsByDrivingLicenseNumber(driving);
    }

    public boolean isPresentCity(String city) {
        return usserRepository.existsByCity(city);
    }

    public boolean isPresentState(String state) {
        return usserRepository.existsByState(state);
    }

    public boolean isPresentPincode(String pincode) {
        return usserRepository.existsByPincode(pincode);
    }

    public boolean isPresentAadhar(String aadhar) {
        return usserRepository.existsByAadharNumber(aadhar);
    }
}
