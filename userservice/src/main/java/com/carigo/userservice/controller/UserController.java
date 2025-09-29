
package com.carigo.userservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.carigo.userservice.dto.UserDTO;
import com.carigo.userservice.model.UserProfile;
import com.carigo.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // ✅ Create User
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<UserProfile> createUser(
            @RequestPart("user") String userJson,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.readValue(userJson, UserDTO.class);

        if (image != null && !image.isEmpty()) {
            userDTO.setImage(image.getBytes());
        }

        UserProfile createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // ✅ Update User
    @PostMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<UserProfile> updateUser(
            @RequestPart("user") String userJson,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.readValue(userJson, UserDTO.class);

        if (image != null && !image.isEmpty()) {
            userDTO.setImage(image.getBytes());
        }

        UserProfile updatedUser = userService.updateProfile(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/present/{id}")
    public ResponseEntity<Boolean> isPresentUserId(@PathVariable("id") String userId) {
        return ResponseEntity.ok(userService.isPresent(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> allUser() {
        List<UserProfile> users = userService.findAllProfiles();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/preson/{id}")
    public ResponseEntity<UserProfile> findByuserID(@PathVariable String id) {
        return ResponseEntity.ok(userService.findByUserID(id));
    }

    @GetMapping("/findemail/{email}")
    public ResponseEntity<Boolean> isPresentEmail(@PathVariable("email") String email) {
        Boolean emailPresent = userService.isEmailpresent(email);
        return ResponseEntity.ok(emailPresent);
    }

    @GetMapping("/search/{input}")
    public ResponseEntity<UserProfile> findUserByAnything(@PathVariable("input") String input) {
        Optional<UserProfile> userOptional = userService.findUser(input);
        return userOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ispresent/id/{id}")
    public ResponseEntity<Boolean> isPresentId(@PathVariable String id) {
        return ResponseEntity.ok(userService.isPresentId(id));
    }

    @GetMapping("/ispresent/fullname/{fullname}")
    public ResponseEntity<Boolean> isPresentFullname(@PathVariable String fullname) {
        return ResponseEntity.ok(userService.isPresentFullname(fullname));
    }

    @GetMapping("/ispresent/mobile/{number}")
    public ResponseEntity<Boolean> isPresentMobile(@PathVariable String number) {
        return ResponseEntity.ok(userService.isPresentMobileNumber(number));
    }

    @GetMapping("/ispresent/driving/{license}")
    public ResponseEntity<Boolean> isPresentDriving(@PathVariable("license") String driving) {
        return ResponseEntity.ok(userService.isPresentDriving(driving));
    }

    @GetMapping("/ispresent/aadhar/{aadhar}")
    public ResponseEntity<Boolean> isPresentAadhar(@PathVariable String aadhar) {
        return ResponseEntity.ok(userService.isPresentAadhar(aadhar));
    }

    @GetMapping("/ispresent/city/{city}")
    public ResponseEntity<Boolean> isPresentCity(@PathVariable String city) {
        return ResponseEntity.ok(userService.isPresentCity(city));
    }

    @GetMapping("/ispresent/state/{state}")
    public ResponseEntity<Boolean> isPresentState(@PathVariable String state) {
        return ResponseEntity.ok(userService.isPresentState(state));
    }

    @GetMapping("/ispresent/pincode/{pincode}")
    public ResponseEntity<Boolean> isPresentPincode(@PathVariable String pincode) {
        return ResponseEntity.ok(userService.isPresentPincode(pincode));
    }
}
