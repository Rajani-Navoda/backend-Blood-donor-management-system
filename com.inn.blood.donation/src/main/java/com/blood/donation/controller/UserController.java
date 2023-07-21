package com.blood.donation.controller;

import com.blood.donation.constants.Constants;
import com.blood.donation.service.UserService;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
