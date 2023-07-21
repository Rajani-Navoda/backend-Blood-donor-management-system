package com.blood.donation.serviceImpl;

import com.blood.donation.constants.Constants;
import com.blood.donation.entity.User;
import com.blood.donation.repo.UserRepo;
import com.blood.donation.service.UserService;
import com.blood.donation.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepo.findByUserName(requestMap.get("userName"));
                if (Objects.isNull(user)){
                    userRepo.save(getUserFromMap(requestMap));
                    return Utils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return Utils.getResponseEntity("User Name already exits", HttpStatus.BAD_REQUEST);
                }
            } else {
                return Utils.getResponseEntity(Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> reuestMap) {
        if (reuestMap.containsKey("userName")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setUserName(requestMap.get("userName"));
        user.setFirstName(requestMap.get("firstName"));
        user.setLastName(requestMap.get("lastName"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setUserType(requestMap.get("userType"));
        user.setStatus("first-login");

        return user;
    }
}
