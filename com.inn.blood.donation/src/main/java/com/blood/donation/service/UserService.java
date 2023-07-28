package com.blood.donation.service;

import com.blood.donation.constants.Constants;
import com.blood.donation.model.User;
import com.blood.donation.repo.UserRepo;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            User user = userRepo.findByUserName(requestMap.get("userName"));
            if (Objects.isNull(user)){
                userRepo.save(getUserFromMap(requestMap));
                return Utils.getResponseEntity("Successfully Registered", HttpStatus.OK);
            } else {
                return Utils.getResponseEntity("User Name already exits", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public User getUserByName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public void updateIsFirstLoginToFalse(User user) {
        user.setIsFirstLogin(false);
        userRepo.save(user);
    }

    public User updateUserDetails(User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return userRepo.save(user);
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setFirstName(requestMap.get("firstName"));
        user.setLastName(requestMap.get("lastName"));
        user.setUserName(requestMap.get("userName"));
        user.setPassword(getEncodedPassword(requestMap.get("password")));
        user.setEmail(requestMap.get("email"));
        user.setUserType(requestMap.get("userType"));
        user.setIsFirstLogin(true);

        return user;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
